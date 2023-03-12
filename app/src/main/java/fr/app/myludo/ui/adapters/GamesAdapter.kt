package fr.app.myludo.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.app.myludo.R
import fr.app.myludo.data.Game
import fr.app.myludo.databinding.AdapterGameBinding
import fr.app.myludo.utils.listeners.OnClickGameListener
class GamesAdapter :
    ListAdapter<Game, GamesAdapter.GamesViewHolder>(DiffCallback) {

    /**
     * The GamesViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [Games] information.
     */
    class GamesViewHolder(
        private var binding: AdapterGameBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Game) {
            binding.photo = game
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * [Games] has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GamesViewHolder {
        return GamesViewHolder(
            AdapterGameBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val game = getItem(position)
        holder.bind(game)
    }
}

/*
class GamesAdapter(
    var mContext: Context,
    private var games: List<Game>,
    private var onClickGameListener: OnClickGameListener

) : RecyclerView.Adapter<GamesAdapter.GamesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        return GamesViewHolder(
            layoutInflater.inflate(
                R.layout.adapter_game,
                parent,
                false
            ),
            mContext
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(
            games[position],
            onClickGameListener
        )
    }

    class GamesViewHolder(
        itemView: View,
        var context: Context
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            game: Game,
            onClickExplorerListener: OnClickGameListener
        ) {

            itemView.setOnClickListener {
                onClickExplorerListener.onGameClick(game)
            }
/*
            PicassoCustom.with()
                .load(game.poster)
//                .error(R.drawable.background_skeleton_poster)
//                .placeholder(R.drawable.background_skeleton_poster)
                .into(itemView.imageview_backdrop_techno)
*/

        }
    }
    
}*/