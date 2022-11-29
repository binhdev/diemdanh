package vocaja.com.conversation.diemdanh.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "learnersessions")
data class LearnerSession(
    @PrimaryKey(autoGenerate = true)
    var Id: Int = 0,
    @ColumnInfo(name = "learner_id") var learnId:Int?,
    @ColumnInfo(name = "session_id") var sessionId:Int?
)
