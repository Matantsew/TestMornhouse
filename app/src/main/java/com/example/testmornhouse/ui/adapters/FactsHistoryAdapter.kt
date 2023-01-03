package com.example.testmornhouse.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testmornhouse.R
import com.example.testmornhouse.model.NumberFact

class FactsHistoryAdapter(
    diffCallback: DiffUtil.ItemCallback<NumberFact> = DiffCallback) :
    ListAdapter<NumberFact, FactsHistoryAdapter.FactViewHolder>(diffCallback) {

    class FactViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        val tVNumber: TextView
        val tVFact: TextView

        init {
            // Define click listener for the ViewHolder's View
            tVNumber = view.findViewById(R.id.tv_number)
            tVFact = view.findViewById(R.id.tv_fact)

            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            // TODO: set click
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FactViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_saved_fact_in_history, viewGroup, false)

        return FactViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: FactViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tVNumber.text = getItem(position).number.toString()
        viewHolder.tVFact.text = getItem(position).fact
    }

}

private object DiffCallback : DiffUtil.ItemCallback<NumberFact>() {
    override fun areItemsTheSame(oldItem: NumberFact, newItem: NumberFact): Boolean {
        return oldItem.hashCode() == newItem.hashCode() // TODO: Consider other comparison option
    }

    override fun areContentsTheSame(oldItem: NumberFact, newItem: NumberFact): Boolean {
        return oldItem.fact == newItem.fact &&
                oldItem.number == newItem.number
    }
}