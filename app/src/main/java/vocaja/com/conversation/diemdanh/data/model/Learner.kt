package vocaja.com.conversation.diemdanh.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "learners")
data class Learner(
    @PrimaryKey var Id: Int,
    @ColumnInfo(name = "name") var name:String?
)
