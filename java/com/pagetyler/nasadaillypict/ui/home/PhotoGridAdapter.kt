/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.pagetyler.nasadaillypict.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pagetyler.nasadaillypict.databinding.GridViewItemBinding
import com.pagetyler.nasadaillypict.network.NasaPicture

class PhotoGridAdapter ( private val onClickListener: OnClickListener ) :
        ListAdapter<NasaPicture,PhotoGridAdapter.NasaPictureViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NasaPictureViewHolder {
        return NasaPictureViewHolder(
            GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: NasaPictureViewHolder, position: Int) {
        val NasaPicture = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(NasaPicture)
        }
        holder.bind(NasaPicture)
    }
    companion object DiffCallback : DiffUtil.ItemCallback<NasaPicture>() {
        override fun areItemsTheSame(oldItem: NasaPicture, newItem: NasaPicture): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: NasaPicture, newItem: NasaPicture): Boolean {
            return oldItem.date == newItem.date
        }
    }

    class NasaPictureViewHolder(private var binding:
                                 GridViewItemBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(NasaPicture: NasaPicture) {
            binding.picture = NasaPicture
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (NasaPicture:NasaPicture) -> Unit) {
        fun onClick(NasaPicture:NasaPicture) = clickListener(NasaPicture)
    }
}

