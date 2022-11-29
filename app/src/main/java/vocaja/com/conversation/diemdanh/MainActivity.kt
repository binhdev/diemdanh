package vocaja.com.conversation.diemdanh

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.Detector.Detections
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.google.gson.Gson
import vocaja.com.conversation.diemdanh.data.AppDatabase
import vocaja.com.conversation.diemdanh.data.entity.LeanerEntity
import vocaja.com.conversation.diemdanh.data.model.LearnerSession
import vocaja.com.conversation.diemdanh.databinding.ActivityMainBinding
import vocaja.com.conversation.diemdanh.util.Constants
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private val requestCodeCameraPermission = 1001
    private lateinit var cameraSource: CameraSource
    private lateinit var barcodeDetector: BarcodeDetector
    private var scannedValue = ""
    private lateinit var binding: ActivityMainBinding
    var counter = 0
    var sessionId = 0
    lateinit var db : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        init()

        if (ContextCompat.checkSelfPermission(
                this@MainActivity, android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            askForCameraPermission()
        } else {
            setupControls()
        }

        val aniSlide: Animation =
            AnimationUtils.loadAnimation(this@MainActivity, R.anim.scanner_animation)
        binding.barcodeLine.startAnimation(aniSlide)
    }

    private fun init(){
        sessionId = intent.getIntExtra(Constants.SESSION_ID, 0)
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, Constants.DATABASE)
            .allowMainThreadQueries()
            .createFromAsset(Constants.DATABASE)
            .build()
    }

    private fun setupControls() {
        barcodeDetector =
            BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.ALL_FORMATS).build()

        cameraSource = CameraSource.Builder(this, barcodeDetector)
            .setRequestedPreviewSize(1920, 1080)
            .setAutoFocusEnabled(true) //you should add this feature
            .build()

        startScan()

        barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {
                Toast.makeText(applicationContext, "Scanner has been closed", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun receiveDetections(detections: Detections<Barcode>) {
                var barcodes = detections.detectedItems
                if (barcodes.size() == 1) {
                    scannedValue = barcodes.valueAt(0).rawValue
                    counter++

                    //Don't forget to add this line printing value or finishing activity must run on main thread
                    runOnUiThread {
                        jsonToObject(scannedValue)
                        setupControls()
                    }
                }else {
                    Log.i("error", "not value")
                }
            }
        })
    }

    private fun startScan(){
        binding.cameraSurfaceView.getHolder().addCallback(object : SurfaceHolder.Callback {
            @SuppressLint("MissingPermission")
            override fun surfaceCreated(holder: SurfaceHolder) {
                try {
                    //Start preview after 1s delay
                    cameraSource.start(holder)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            @SuppressLint("MissingPermission")
            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
                try {
                    cameraSource.start(holder)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                cameraSource.stop()
            }
        })
    }
    private fun askForCameraPermission() {
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(android.Manifest.permission.CAMERA),
            requestCodeCameraPermission
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == requestCodeCameraPermission && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupControls()
            } else {
                Toast.makeText(applicationContext, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun jsonToObject(json:String){
        val gson = Gson()
        val std = gson.fromJson(json, LeanerEntity::class.java)

        val learner = db.learnerDao().find(std.id)
        if(learner != null){
            val learnerSession = db.learnerSessionDao().find(learner.Id, sessionId)
            if(learnerSession == null){
                val ls = LearnerSession(0, learner.Id, sessionId)
                db.learnerSessionDao().insert(ls)
                val toneGen1 = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
                toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150)
            }
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        cameraSource.stop()
    }
}