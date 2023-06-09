package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemory : PostRepository {

    private var post = List(1000) {
        Post(
            id = it.toLong() + 1,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Привет, это ВТОРОЙ ПОСТ и новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            likedByMe = false,
        )
    }.reversed()

    private val data = MutableLiveData(post)

    override fun getData(): LiveData<List<Post>> = data
    override fun likeById(id: Long) {
        post = post.map { post ->
            if (post.id == id) {
                post.copy(
                    likedByMe = !post.likedByMe,
                    likes = if (post.likedByMe) {
                        post.likes - 1
                    } else {
                        post.likes + 1
                    }
                )
            } else {
                post
            }
        }
        data.value = post
    }

    override fun shareById(id: Long) {
        post = post.map { post ->
            if (post.id == id) {
                post.copy(
                    shares = post.shares + 1,
                )
            } else {
                post
            }
        }
    }


//    override fun share() {
//        post = post.copy(
//            shares = post.shares + 1
//        )
//        data.value = post
//    }
}
