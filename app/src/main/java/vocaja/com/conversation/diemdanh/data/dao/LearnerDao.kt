package vocaja.com.conversation.diemdanh.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import vocaja.com.conversation.diemdanh.data.model.Learner
import vocaja.com.conversation.diemdanh.util.Constants

@Dao
interface LearnerDao {
    @Query("SELECT * FROM learners")
    fun all() : List<Learner>

    @Query("SELECT * FROM learners WHERE Id=:Id LIMIT 1")
    fun find(Id: Int): Learner

    @Update(entity = Learner::class)
    fun update(origin: Learner)

}