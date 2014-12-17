package org.l3cache.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.l3cache.model.Post;
import org.l3cache.model.PostId;
import org.l3cache.model.PostSel;
import org.l3cache.model.WritePost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostManager {
	private static final int RECOMMENDATION_LISTS = 0;
	private static final int RECENT_LISTS = 1;
	private static final int POPULAR_LISTS = 2;
	
	private SqlSession sqlSession;
	private static final Logger log = LoggerFactory
			.getLogger(PostManager.class);
	
	
	public PostManager(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<Post> getRecentlyLists(int start, int uid) {
		start = (start - 1) * 20;
		PostSel postSel = new PostSel(start, uid);
		return sqlSession.selectList("PostMapper.selectRecentlyList", postSel);
	}

	public Post getPostDetail(long pid) {
		return sqlSession.selectOne("PostMapper.selectOnePost", pid);
	}

	public void savePost(WritePost post) {
		sqlSession.insert("PostMapper.create", post);
	}

	public void updateWithImage(WritePost post) {
		sqlSession.update("PostMapper.updateWithImage", post);
	}

	public void updateWithoutImage(WritePost post) {
		sqlSession.update("PostMapper.updateWithoutImage", post);
	}

	public String getPostImageFilePath(long pid) {
		Post post = getPostDetail(pid);
		return post.getImgUrl();
	}

	public boolean isExistentPost(long pid) {
		Post post = getPostDetail(pid);
		if (post == null) {
			return false;
		}
		return true;
	}

	public void deletePost(long pid, int uid) {
		PostId postId = new PostId(pid, uid);
		sqlSession.delete("PostMapper.deletePost", postId);
	}

	public void likePost(long pid, int uid) {
		PostId postId = new PostId(pid, uid);
		sqlSession.insert("PostMapper.likePost", postId);
	}

	public void unlikePost(long pid, int uid) {
		PostId postId = new PostId(pid, uid);
		sqlSession.delete("PostMapper.unlikePost", postId);
	}

	public void readPost(long pid) {
		sqlSession.update("PostMapper.readPost", pid);
	}

	public int getTotalRows() {
		return sqlSession.selectOne("PostMapper.foundRows");
	}

	public List<Post> getUserPostsList(int uid, int start) {
		start = (start - 1) * 20;
		PostSel postSel = new PostSel(start, uid);
		return sqlSession.selectList("PostMapper.selectUserPostsList", postSel);
	}

	public int getUserPostsCount(int uid) {
		return sqlSession.selectOne("PostMapper.countUserPostsList", uid);
	}

	public int getUserLikesCount(int uid) {
		return sqlSession.selectOne("PostMapper.countUserLikesList", uid);
	}

	public List<Post> getUserLikesList(int uid, int start) {
		start = (start - 1) * 20;
		PostSel postSel = new PostSel(start, uid);
		return sqlSession.selectList("PostMapper.selectUserLikesList", postSel);
	}

	public Object getPostsLists(int start, int id, int sort) {
		if(start<1 || id <1){
			throw new IllegalArgumentException();
		}
		
		switch (sort) {
		case RECOMMENDATION_LISTS:
			return getRecentlyLists(start, id);
		case RECENT_LISTS:
			return getRecentlyLists(start, id);
		case POPULAR_LISTS:
			return getRecentlyLists(start, id);

		default:
			return getRecentlyLists(start, id);
		}
	}

}
