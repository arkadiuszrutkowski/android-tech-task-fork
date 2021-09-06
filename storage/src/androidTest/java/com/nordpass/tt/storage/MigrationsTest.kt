package com.nordpass.tt.storage

import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MigrationsTest {
    @get:Rule
    val helper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        TodoDatabase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    fun validateAllMigrations() {
        helper.createDatabase(DATABASE_NAME, 1)
        helper.runMigrationsAndValidate(DATABASE_NAME, 3, false, *Migrations.allMigrations)
    }

    companion object {
        private const val DATABASE_NAME = "test_db"
    }
}