package com.ssafy.aongbucks_user.activity

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.config.ApplicationClass
import com.ssafy.aongbucks_user.databinding.ActivityMainBinding
import com.ssafy.aongbucks_user.viewModel.MainActivityViewModel

private const val TAG = "MainActivity_싸피"
class MainActivity : AppCompatActivity() {
    private val activityViewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    // NFC 사용
    var nfcAdapter: NfcAdapter? = null
    var pIntent: PendingIntent? = null
    lateinit var filters: Array<IntentFilter>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNdef()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
    }

    fun hideBottomNav(state: Boolean) {
        if (state) binding.bottomNavigation.visibility = View.GONE
        else binding.bottomNavigation.visibility = View.VISIBLE
    }

    fun logout() {
        ApplicationClass.sharedPreferencesUtil.deleteUser()

        val intent = Intent(this, LoginActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        startActivity(intent)
    }


    /**
     * NFC
     */

    private fun setNdef() {
        // NFC
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        // foreground 기능 설정을 위한 코드
        var i = Intent(this, MainActivity::class.java) // 부모 activity
        i.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP // single top
        pIntent = PendingIntent.getActivity(this, 0, i, 0)

        // 인식할 nfc 설정
        val ndefFilter = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)
        ndefFilter.addDataType("text/plain")
        filters = arrayOf(ndefFilter)
    }

    private fun getNFCData(intent: Intent) {
        // TAG가 태깅되었을 때
        if (intent.action.equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {

            val rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)

            if (rawMsgs != null) {
                val message = arrayOfNulls<NdefMessage>(rawMsgs.size)

                for (i in rawMsgs.indices) {
                    message[i] = rawMsgs[i] as NdefMessage
                }

                // 실제 저장되어 있는 데이터를 추출
                val record_data = message[0]!!.records[0]
                val record_type = String(record_data.type)
                val record_content = record_data.payload

                // NFC에서 table number을 읽어서 저장
                if (record_type.equals("T")) { // text
                    activityViewModel.readGiftCard(
                        String(
                            record_content,
                            3,
                            record_content.size - 3
                        )
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // 포그라운드 기능 활성화 (하드웨어때메 퍼미션 필요)
        nfcAdapter!!.enableForegroundDispatch(
            this,
            pIntent,
            filters,
            null
        ) // null : 모든 타입을 다 받겠다.
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e(TAG, "onNewIntent: called...",)
        if (activityViewModel.nfcFlag && intent?.action.equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
            getNFCData(intent!!)
        }
    }

    override fun onPause() {
        super.onPause()
        nfcAdapter!!.disableForegroundDispatch(this)
    }
}
