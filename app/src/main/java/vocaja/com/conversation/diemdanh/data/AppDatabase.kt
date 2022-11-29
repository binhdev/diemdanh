package vocaja.com.conversation.diemdanh.data

import androidx.room.Database
import androidx.room.RoomDatabase
import vocaja.com.conversation.diemdanh.data.dao.LearnerDao
import vocaja.com.conversation.diemdanh.data.dao.LearnerSessionDao
import vocaja.com.conversation.diemdanh.data.dao.SessionDao
import vocaja.com.conversation.diemdanh.data.model.Learner
import vocaja.com.conversation.diemdanh.data.model.LearnerSession
import vocaja.com.conversation.diemdanh.data.model.Session
import vocaja.com.conversation.diemdanh.util.Constants

@Database(entities = [Learner::class, Session::class, LearnerSession::class], version = Constants.DB_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun learnerDao() : LearnerDao
    abstract fun sessionDao() : SessionDao
    abstract fun learnerSessionDao() : LearnerSessionDao
}
