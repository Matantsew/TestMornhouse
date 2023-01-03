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
    private val onFactItemClickListener: OnFactItemClickListener,
    diffCallback: DiffUtil.ItemCallback<NumberFact> = DiffCallback)
    : ListAdapter<NumberFact, FactsHistoryAdapter.FactViewHolder>(diffCallback) {

    class FactViewHolder(
        view: View,
        private val onFactItemClickListener: OnFactItemClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener {

        val tVNumber: TextView
        val tVFact: TextView

        init {

            tVNumber = view.findViewById(R.id.tv_number)
            tVFact = view.findViewById(R.id.tv_fact)

            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            onFactItemClickListener.onFactItemClick(NumberFact(tVNumber.text.toString().toInt(), tVFact.text.toString()))
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FactViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_saved_fact_in_history, viewGroup, false)

        return FactViewHolder(view, onFactItemClickListener)
    }

    override fun onBindViewHolder(viewHolder: FactViewHolder, position: Int) {

        viewHolder.tVNumber.text = getItem(position).number.toString()
        viewHolder.tVFact.text = getItem(position).fact
    }

    interface OnFactItemClickListener {
        fun onFactItemClick(selectedNumberFact: NumberFact)
    }
}

private object DiffCallback : DiffUtil.ItemCallback<NumberFact>() {
    override fun areItemsTheSame(oldItem: NumberFact, newItem: NumberFact): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: NumberFact, newItem: NumberFact): Boolean {
        return oldItem.fact == newItem.fact &&
                oldItem.number == newItem.number
    }
}