package vocaja.com.conversation.diemdanh.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import vocaja.com.conversation.diemdanh.data.model.LearnerSession

@Dao
interface LearnerSessionDao {
    @Query("SELECT * FROM learnersessions")
    fun all() : List<LearnerSession>

    @Query("SELECT * FROM learnersessions WHERE Id=:Id LIMIT 1")
    fun find(Id: Int): LearnerSession

    @Query("SELECT * FROM learnersessions WHERE learner_id=:learnerId AND session_id=:sessionId LIMIT 1")
    fun find(learnerId: Int, sessionId: Int): LearnerSession

    @Update(entity = LearnerSession::class)
    fun update(origin: LearnerSession)

    @Insert(entity = LearnerSession::class)
    fun insert(origin: LearnerSession)
}