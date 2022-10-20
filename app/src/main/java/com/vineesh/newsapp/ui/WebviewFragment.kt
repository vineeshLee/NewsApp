package com.vineesh.newsapp

import Articles
import android.os.Bundle
import android.view.*
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.vineesh.newsapp.databinding.FragmentWebviewBinding


class WebviewFragment : Fragment() {
    lateinit var webView:WebView
    lateinit var item:Articles
    private lateinit var binding: FragmentWebviewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebviewBinding.inflate(inflater, container, false)
        if (arguments?.containsKey("object")==true)
        {
            item =arguments?.getSerializable("object") as Articles
            arguments?.clear()
        }
        item.let {

            binding.progressbar.visibility=View.VISIBLE
            binding.webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    url: String
                ): Boolean {
                    view.loadUrl(url)
                    return true


                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    if (binding.progressbar.isVisible) {
                        binding.progressbar.visibility=View.GONE
                    }
                }

            }
            val webSetting: WebSettings = binding.webView.settings
            webSetting.javaScriptEnabled = true
            binding.webView.webViewClient = WebViewClient()

            binding.webView.canGoBack()
            binding.webView.setOnKeyListener(View.OnKeyListener { v , keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_BACK

                    && event.action == MotionEvent.ACTION_UP
                    && binding.webView.canGoBack()){
                    binding.webView.goBack()
                    return@OnKeyListener true
                }
                false
            })


            binding.webView.loadUrl(item.url.toString())
            binding.webView.settings.javaScriptEnabled = true
            binding.webView.settings.allowContentAccess = true
            binding.webView.settings.domStorageEnabled = true
            binding.webView.settings.useWideViewPort = true


        }

        return binding.root
    }


}