package com.tikalk.mobileevent.mobileevent

import android.content.Context
import android.provider.CallLog
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.tikalk.mobileevent.mobileevent.data.CallLogDao
import com.tikalk.mobileevent.mobileevent.data.CallLogManager
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Rule
import android.support.test.rule.GrantPermissionRule



/**
 * Created by shaulr on 02/08/2017.
 */
@RunWith(AndroidJUnit4::class)
class CallLogTest {
    private val TAG: String = "CallLogTest"

    lateinit var context : Context
    lateinit var manager : CallLogManager

    @Rule @JvmField var permissionRule = GrantPermissionRule.grant(android.Manifest.permission.WRITE_CALL_LOG, android.Manifest.permission.READ_CALL_LOG)

    @Before
    fun setup() {
        context = InstrumentationRegistry.getContext()
        manager = CallLogManager(context)
    }

    @Test
    fun callLogTest() {
        val logs = ArrayList<CallLogDao>()
        logs.add(CallLogDao("123123", System.currentTimeMillis(), 1000, CallLog.Calls.OUTGOING_TYPE,true,"Test 1"))
        logs.add(CallLogDao("456456", System.currentTimeMillis(), 2000, CallLog.Calls.INCOMING_TYPE,true,"Test 2"))

        assertEquals("managed to write two records", manager.write(logs), 2)
        assertTrue("managed to read at least two records", manager.read().size >= 2)
    }



}