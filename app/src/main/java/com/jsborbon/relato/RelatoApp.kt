package com.jsborbon.relato

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.*
import android.os.Bundle

class RelatoApp : Application() {
    val database: AppDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "relato_database"
        ).build()
    }
}

@Entity(tableName = "communication")
data class Communication(
    @PrimaryKey val id: Int,
    val message: String
)

@Entity(tableName = "category")
data class Category(
    @PrimaryKey val id: Int,
    val name: String
)

@Entity(tableName = "review")
data class Review(
    @PrimaryKey val id: Int,
    val content: String
)

@Database(entities = [Communication::class, Category::class, Review::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun communicationDao(): CommunicationDao
    abstract fun categoryDao(): CategoryDao
    abstract fun reviewDao(): ReviewDao
}

@Dao
interface CommunicationDao {
    @Insert
    suspend fun insert(communication: Communication)

    @Query("SELECT * FROM communication")
    suspend fun getAll(): List<Communication>
}

@Dao
interface CategoryDao {
    @Insert
    suspend fun insert(category: Category)

    @Query("SELECT * FROM category")
    suspend fun getAll(): List<Category>
}

@Dao
interface ReviewDao {
    @Insert
    suspend fun insert(review: Review)

    @Query("SELECT * FROM review")
    suspend fun getAll(): List<Review>
}
/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentViewTest()
        }
    }
}*/

@Composable
fun ContentViewTest() {
    Text("Welcome to Relato App")
}
