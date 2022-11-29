package vocaja.com.conversation.diemdanh.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import vocaja.com.conversation.diemdanh.data.model.Session

@Dao
interface SessionDao {
    @Query("SELECT * FROM sessions")
    fun all() : List<Session>

    @Query("SELECT * FROM sessions WHERE Id=:Id LIMIT 1")
    fun find(Id: Int): Session

    @Update(entity = Session::class)
    fun update(origin: Session)

}