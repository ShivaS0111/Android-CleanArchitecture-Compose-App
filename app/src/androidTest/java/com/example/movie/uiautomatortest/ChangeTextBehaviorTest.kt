package com.example.movie.uiautomatortest

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import com.example.movie.MainActivity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class ChangeTextBehaviorTest {

    private lateinit var mDevice: UiDevice

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(getInstrumentation())

        // Start from the home screen
        mDevice.pressHome()

        // Wait for launcher
        val launcherPackage = launcherPackageName
        assertThat(launcherPackage, notNullValue())
        mDevice.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT.toLong()
        )

        // Launch the blueprint app
        val context: Context = getApplicationContext()
        val intent: Intent? = context.packageManager
            .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) // Clear out any previous instances
        context.startActivity(intent)

        // Wait for the app to appear
        mDevice.wait(
            Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT.toLong()
        )
    }

    @Test
    fun checkPreconditions() {
        assertThat(mDevice, notNullValue())
    }

    @Test
    fun moviesListTest(){

        composeTestRule.onNodeWithText("Movie List")
            .assertIsDisplayed()

        // Simulate the coroutine completion and wait for the UI to update
        runBlocking {
            composeTestRule.awaitIdle()
        }
        composeTestRule.onNodeWithTag("list")
            .assertIsDisplayed()
    }
    @Test
    fun loaderTest(){

    }

    @Test
    fun showMoviesList(){
        // Wait for the AppScreen composable to be drawn on the screen. We do this waiting because some apps
        /*// startup with a splash screen not the composable:
        mDevice.wait(Until.hasObject(By.res("column" )), 5_000)

        // Enter the name into the inputTextField
        val inputTextField = mDevice.findObject(By.res("inputTextField"))
        inputTextField.text = "John Cena"

        // Press the button
        val button = mDevice.findObject(By.res("button"))
        button.click()

        // Verify that the out put is the expected out put:
        val outputTextField = mDevice.findObject(By.res("outputTextField"))
        val result = outputTextField.text

        assertEquals("Welcome, John Cena!", result)*/

        val loadingText: UiObject = mDevice.findObject(
            UiSelector().resourceId("progress")
        )
        assertTrue(loadingText.waitUntilGone(10000))


        // Check the result text
        val resultText: UiObject = mDevice.findObject(UiSelector().description("ResultText"))
        val result = resultText.text
        assertEquals("Expected API data", result)


    }

    @Test
    fun testChangeText_sameActivity() {
        // Type text and then press the button.
        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "editTextUserInput"))
            .setText(STRING_TO_BE_TYPED)
        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "changeTextBt"))
            .click()

        // Verify the test is displayed in the Ui
        val changedText: UiObject2 = mDevice
            .wait(
                Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "textToBeChanged")),
                500 /* wait 500ms */
            )
        assertThat(changedText.text, `is`(equalTo(STRING_TO_BE_TYPED)))
    }

    @Test
    fun testChangeText_newActivity() {
        // Type text and then press the button.
        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "editTextUserInput"))
            .setText(STRING_TO_BE_TYPED)
        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "activityChangeTextBtn"))
            .click()

        // Verify the test is displayed in the Ui
        val changedText: UiObject2 = mDevice.wait(
                Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "show_text_view")),
                500 /* wait 500ms */
            )
        assertThat(changedText.text, `is`(equalTo(STRING_TO_BE_TYPED)))
    }

    private val launcherPackageName: String
        /**
         * Uses package manager to find the package name of the device launcher. Usually this package
         * is "com.android.launcher" but can be different at times. This is a generic solution which
         * works on all platforms.`
         */
        get() {
            // Create launcher Intent
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)

            // Use PackageManager to get the launcher package name
            val pm = getApplicationContext<Context>().packageManager
            val resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
            return resolveInfo!!.activityInfo.packageName
        }

    companion object {
        private const val BASIC_SAMPLE_PACKAGE =
            "com.example.test_movie_app"

        private const val LAUNCH_TIMEOUT = 5000

        private const val STRING_TO_BE_TYPED = "UiAutomator"
    }
}