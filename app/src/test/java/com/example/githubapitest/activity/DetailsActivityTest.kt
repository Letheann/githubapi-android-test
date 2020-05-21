package com.example.githubapitest.activity

import android.content.Intent
import androidx.test.InstrumentationRegistry
import com.example.githubapitest.helper.extensions.extras
import com.example.githubapitest.model.users.Repos
import com.example.githubapitest.ui.activity.DetailsActivity
import junit.framework.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DetailsActivityTest {
    @Test
    fun assertIntent() {
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, DetailsActivity::class.java)
        val repos = Repos()
        intent.putExtra("repos", repos)
        Assert.assertEquals(intent.extras("repos"), repos)
    }
}