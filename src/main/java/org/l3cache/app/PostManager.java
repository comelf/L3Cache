package org.l3cache.app;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.l3cache.model.Post;
import org.l3cache.model.PostId;
import org.l3cache.model.PostSel;
import org.l3cache.model.WritePost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostManager {
	private static final int SQL_SUCCESS = 1;
	private SqlSession sqlSession;
	private static final Logger log = LoggerFactory
			.getLogger(PostManager.class);

	public PostManager(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<Post> getRecentlyLists(int start, int uid) {
		start = (start -1) * 20;
		PostSel postSel = new PostSel(start, uid);
		return sqlSession.selectList("PostMapper.selectRecentlyList", postSel);
	}

	public Post getPostDetail(long pid) {
		return sqlSession.selectOne("PostMapper.selectOnePost", pid);
	}

	public boolean savePost(WritePost post) {
		if (sqlSession.insert("PostMapper.create", post) == SQL_SUCCESS) {
			return true;
		}
		return false;
	}

	public boolean updateWithImage(WritePost post) {
		if (sqlSession.update("PostMapper.updateWithImage", post) == SQL_SUCCESS) {
			return true;
		}
		return false;
	}

	public boolean updateWithoutImage(WritePost post) {
		if (sqlSession.update("PostMapper.updateWithoutImage", post) == SQL_SUCCESS) {
			return true;
		}
		return false;
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

	public boolean deletePost(long pid, int uid) {
		PostId postId = new PostId(pid, uid);
		if (sqlSession.delete("PostMapper.deletePost", postId) == SQL_SUCCESS) {
			return true;
		}

		return false;
	}

	public boolean likePost(long pid, int uid) {
		PostId postId = new PostId(pid, uid);
		if (sqlSession.insert("PostMapper.likePost", postId) == SQL_SUCCESS) {
			log.debug("like PostId ={} UserId ={}", pid, uid);
			return true;
		}
		return false;
	}

	public boolean unlikePost(long pid, int uid) {
		PostId postId = new PostId(pid, uid);
		if (sqlSession.delete("PostMapper.unlikePost", postId) == SQL_SUCCESS) {
			log.debug("Unlike PostId ={} UserId ={}", pid, uid);
			return true;
		}
		return false;
	}

	public boolean readPost(long pid) {
		if (sqlSession.update("PostMapper.readPost", pid) == SQL_SUCCESS) {
			return true;
		}
		return false;
	}

	public int getTotalRows() {
		
		int total = sqlSession.selectOne("PostMapper.foundRows");
		System.out.println("total rows = " +total);
		return total;
	}

	public List<Post> getUserPostsList(int uid, int start) {
		start = (start -1) * 20;
		PostSel postSel = new PostSel(start, uid);
		return sqlSession.selectList("PostMapper.selectUserPostsList", postSel);
	}

	public int getUserPostsCount(int uid) {
		return sqlSession.selectOne("PostMapper.countUserPostsList", uid);
	}

	public int getUserLikesCount(int uid) {
		return sqlSession.selectOne("PostMapper.countUserLikesList", uid);
	}

	public Object getUserLikesList(int uid, int start) {
		start = (start -1) * 20;
		PostSel postSel = new PostSel(start, uid);
		return sqlSession.selectList("PostMapper.selectUserLikesList", postSel);
	}

}
