package com.example.byowebview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView

class EventsView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events_view)

        val eventsList : MutableList<CapturedEvent> = intent.getSerializableExtra(EVENTS_MESSAGE) as MutableList<CapturedEvent>

        val listView = findViewById<ListView>(R.id.events_listview)
        listView.adapter = EventsAdapter(this, eventsList)
    }

    private class EventsAdapter(context: Context, events: MutableList<CapturedEvent>) : BaseAdapter() {
        private val mContext: Context
        private val mEvents: MutableList<CapturedEvent>

        init {
            mContext = context
            mEvents = events
        }

        override fun getCount(): Int {
            return mEvents.count()
        }
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }
        override fun getItem(position: Int): String {
            return mEvents.get(position).eventName.toString()
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var tv = TextView(mContext)
            val event = mEvents.get(position)
            tv.text = event.eventName + " - " + event.payload
            return tv
        }
    }
}