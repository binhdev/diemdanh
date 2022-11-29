package vocaja.com.conversation.diemdanh.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sessions")
data class Session(
    @PrimaryKey var Id: Int,
    @ColumnInfo(name = "name") var name:String?
)
