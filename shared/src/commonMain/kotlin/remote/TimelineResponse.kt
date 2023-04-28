package remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TimelineResponse(
    val feed: List<FeedResponse>,
    val cursor: String? = null
)

@Serializable
data class FeedResponse(
    val post: PostView,
    val reply: ReplyRef? = null,
)

@Serializable
data class ReplyRef(
    val root: PostView,
    val parent: PostView
)

@Serializable
data class PostView(
    val uri: String,
    val cid: String,
    val author: ProfileViewBasic,
    val record: Record,
    val indexedAt: String,
    val replyCount: Int? = null,
    val repostCount: Int? = null,
    val likeCount: Int? = null,
//    TODO: val viewer,
//    TODO: val embed
)

@Serializable
data class ProfileViewBasic(
    val did: String,
    val handle: String,
    val displayName: String? = null,
    val avatar: String? = null,
//    TODO: val viewer,
)

@Serializable
sealed class Record {
    abstract val createdAt: String
}

@Serializable
@SerialName("app.bsky.graph.follow")
data class Follow(
    val subject: String,
    override val createdAt: String
) : Record()

@Serializable
@SerialName("app.bsky.feed.like")
data class Like(
//    TODO: val subject,
    override val createdAt: String
) : Record()

@Serializable
@SerialName("app.bsky.feed.repost")
data class Repost(
//    TODO: val subject,
    override val createdAt: String
) : Record()

@Serializable
@SerialName("app.bsky.feed.post")
data class Post(
    val text: String,
    override val createdAt: String,
//    TODO: val entities
//    TODO: val facets
//    TODO: val reply
//    TODO: val embed
) : Record()
