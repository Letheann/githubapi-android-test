package com.example.githubapitest.viewmodel

import android.content.Intent
import androidx.test.InstrumentationRegistry
import com.example.githubapitest.helper.extensions.extras
import com.example.githubapitest.model.FilterParameters
import com.example.githubapitest.ui.activity.FilterActivity
import junit.framework.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FilterViewModelTest {
    @Test
    fun assertIntent() {
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, FilterActivity::class.java)
        val filter = FilterParameters()
        intent.putExtra("filter", filter)
        Assert.assertEquals(intent.extras<FilterParameters>("filter"), filter)
    }
}