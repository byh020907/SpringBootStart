package com.example.webservice.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.webservice.domain.posts.Posts;
import com.example.webservice.domain.posts.PostsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
	@Autowired
	PostsRepository postsRepository;
	
	@After
	public void cleanup(){
		postsRepository.deleteAll();
	}
	
	@Test
	public void BoardSaveAndReload(){
		//given
		postsRepository.save(Posts.builder()
				.title("테스트 게시글")
				.content("테스트 본문")
				.author("byh020907@naver.com")
				.build());
		
		//when
		List<Posts> postsList = postsRepository.findAll();
		
		//then
		Posts posts=postsList.get(0);
		assertThat(posts.getTitle(),is("테스트 게시글"));
		assertThat(posts.getContent(), is("테스트 본문"));
	}
	
	@Test
    public void BaseTimeEntity_등록 () {
        //given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                .title("테스트 게시글")
                .content("테스트 본문")
                .author("jojoldu@gmail.com")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertTrue(posts.getCreatedDate().isAfter(now));
        assertTrue(posts.getModifiedDate().isAfter(now));
    }
}
