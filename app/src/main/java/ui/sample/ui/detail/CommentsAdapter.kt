package ui.sample.ui.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ui.sample.R
import ui.sample.data.model.Comment
import ui.sample.ui.custom.CircularImageView
import ui.sample.utils.ImageUtils

/**
 * Created by Shashank on 21/10/2017.
 */
class CommentsAdapter(private var commentList: List<Comment>) : RecyclerView.Adapter<CommentsAdapter
.CommentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommentsViewHolder {
        return CommentsViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.layout_comment, parent, false))
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    fun setItems(commentList: List<Comment>) {
        this.commentList = commentList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CommentsViewHolder?, position: Int) {
        holder?.bind(commentList[position])
    }

    class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val profileImageView: CircularImageView = itemView.findViewById(R.id.imageview_profile)
        private val nameTextView: TextView = itemView.findViewById(R.id.textview_name)
        private val dateTextView: TextView = itemView.findViewById(R.id.textview_date)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.textview_description)

        fun bind(comment: Comment) {
            profileImageView.setImageResource(ImageUtils.getImageResId(comment.author.imageId))
            nameTextView.text = comment.author.name
            dateTextView.text = comment.date
            descriptionTextView.text = comment.text
        }
    }
}