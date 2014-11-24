package org.l3cache.app;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.l3cache.model.Post;

public class PostManager {
	SqlSession sqlSession;
	
	public PostManager(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<Post> getRecentlyLists(int start) {
		start = (start -1) *20;
		return sqlSession.selectList("PostMapper.selectRecentlyList", start);
	}

	public Post getPostDetail(long pid) {
		return sqlSession.selectOne("PostMapper.selectOnePost", pid);
	}

	public int savePost(Post post) {
		return sqlSession.insert("PostMapper.create", post);
	}

}
