package com.example.day3pay

import android.os.Bundle
import android.util.Base64
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WebviewActivity : AppCompatActivity() {
    lateinit var webview:WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        webview = findViewById(R.id.webView)
       // webview.loadUrl("https://yahoo.com")
        webview.settings.javaScriptEnabled = true
        webview.addJavascriptInterface(WebAppInterface(this), "Android")

        webview.webViewClient = WebViewClient()
        webview.webChromeClient = WebChromeClient()

        // Add interface to connect JS with Android
        webview.addJavascriptInterface(WebAppInterface(this), "MAndroid")

        // Load your HTML file
        webview.loadUrl("file:///android_asset/myscript.html")


//        val unencodedHtml =
//            "<html><body>'%23' is the percent code for ‘#‘ </body></html>";
//        val encodedHtml = Base64.encodeToString(unencodedHtml.toByteArray(), Base64.NO_PADDING)
//        webview.loadData(encodedHtml, "text/html", "base64")
        val html = """
            <html>
            <body>
                <h2>JS Demo</h2>
                <script src="file:///android_asset/script.js"></script>
                <script>
                    document.write(greetUser("Android Developer"));
                </script>
            </body>
            </html>
        """.trimIndent()
//        webview.evaluateJavascript("greetUser('Kotlin')") { result ->
//            println("JS returned: $result")
//        }
       // webview.loadDataWithBaseURL(null, html, "text/html", "utf-8", null)

    }
}