package com.vineesh.newsapp

import Articles
import Request
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.vineesh.newsapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class HomeFragment : Fragment(R.layout.fragment_home), NewsListAdapter.NewsItemListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var newsAdapter: NewsListAdapter
    lateinit var newsList:ArrayList<Articles>
    var isReversed:Boolean=false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        newsAdapter = NewsListAdapter(this)
        binding.RecyclerViewNewsList.layoutManager =  LinearLayoutManager(requireContext())
        binding.RecyclerViewNewsList.adapter = newsAdapter


        /**fetch data from Server using WebAPI **/
        fetchCurrencyData()
        return binding.root
    }

    private fun fetchCurrencyData()
    {
        /**shimmer library using for loading progress **/
        binding.shimmerLayout.visibility=View.VISIBLE
        binding.shimmerLayout.startShimmer()
        binding.shimmerLayout.startLayoutAnimation()

        /**calling API inside a Coroutine scope and updating UI **/

        GlobalScope.launch{
            val url = URL("https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json")
            val connection  = url.openConnection() as HttpsURLConnection

            if(connection.responseCode == 200)
            {
                val inputSystem = connection.inputStream
                val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")
                val request = Gson().fromJson(inputStreamReader, Request::class.java)

                withContext(Dispatchers.Main) {
                    updateUI(request)
                }

                inputStreamReader.close()
                inputSystem.close()
            }
            else
            {
                binding.shimmerLayout.visibility=View.GONE
                binding.shimmerLayout.stopShimmer()
            }
        }


        /**Filter Buttons **/
        binding.newToOld.setOnClickListener {
            newsList.reverse()
            newsAdapter.setItems(newsList)
            newsAdapter.notifyDataSetChanged()
            isReversed=true
            binding.filterLayout.visibility=View.GONE
        }

        binding.oldToNew.setOnClickListener {
            if (isReversed) {
                isReversed = false
                newsList.reverse()
                newsAdapter.setItems(newsList)
                newsAdapter.notifyDataSetChanged()
                binding.filterLayout.visibility = View.GONE
            }

        }

        binding.imvFilter.setOnClickListener {
            binding.filterLayout.visibility=View.VISIBLE
        }

    }


    private fun updateUI(request: Request)
    {
        newsList=request.articles
        newsAdapter.setItems(newsList)
        binding.shimmerLayout.visibility=View.GONE
        binding.shimmerLayout.stopShimmer()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      /*  binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_blankFragment_to_blankFragment2)
        }*/
    }

    override fun onClickedNews(item: Articles) {

        findNavController().navigate(R.id.action_homeFragment_to_webviewFragment,
            bundleOf("object" to item))
    }


}