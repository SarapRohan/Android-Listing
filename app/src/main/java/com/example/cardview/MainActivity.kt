package com.example.cardview

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    private lateinit var carsList: ArrayList<Car>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindJSONDataInCarsList()


    }

    private fun loadJSON(fileName: String): String {
        return this.applicationContext.assets.open(fileName).bufferedReader().use { reader ->
            reader.readText()
        }
    }



    private fun bindJSONDataInCarsList() {
        carsList = ArrayList<Car>()
        var jsonString: String = loadJSON("response.json")
        var jsonObject = JSONObject(jsonString)
        var data = jsonObject.getJSONObject("data")
        var listingData = data.getJSONObject("listingResponse")
        var cars = listingData.getJSONArray("cars")
        for (i in 0 until cars.length()){
            val car = Car()
            val carJSONObject = cars.getJSONObject(i)
            val carMetadata = carJSONObject.getJSONObject("carLevelMetaData")
            val carHeader = carMetadata.getJSONObject("carHeader")
            val carSubHeader = carMetadata.getJSONObject("carSubHeader")
            car.name =  carHeader.getString("text")
            car.description = carSubHeader.getString("text")
            car.price = carMetadata.getString("startingPrice")
            car.imgUrl = carJSONObject.getString("imgUrl")
            val packages = carJSONObject.getJSONArray("packages")
            for (j in 0 until packages.length()) {
                val pack = CarPackage()
                val packageJSONObject = packages.getJSONObject(j)
                val displayMarkup = packageJSONObject.getJSONObject("displayMarkup")
                val priceJSONArray = displayMarkup.getJSONArray("price")
                val priceJSON = priceJSONArray.getJSONObject(0)
                pack.price = priceJSON.getString("text")
                val pointers = displayMarkup.getJSONArray("pointers")
                val pointerList = pointers.getJSONArray(0)
                for (k in 0 until pointerList.length()) {
                    val displayOption = pointerList.getJSONObject(k)
                    pack.deliveryOption = pack.deliveryOption.plus(" ").plus(displayOption.getString("text"))
                }
                car.carPackages.add(pack)
            }
            carsList.add(car)
        }

        recyclerView = findViewById<RecyclerView>(R.id.carRecyclerview)

        viewAdapter = MyAdapter(carsList)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = viewAdapter

    }
}


