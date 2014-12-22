package org.l3cache.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.l3cache.dto.Response;
import org.l3cache.model.Post;
import org.l3cache.model.PostId;
import org.l3cache.model.PostSel;
import org.l3cache.model.WritePost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.utils.ResultCode;


@Component
public class PostManager {
	private static final int RECOMMENDATION_LISTS = 0;
	private static final int RECENT_LISTS = 1;
	private static final int POPULAR_LISTS = 2;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private Recommender recommander;
	
	private static final Logger LOG = LoggerFactory
			.getLogger(PostManager.class);
	
	public PostManager() {
		
	}
	
	
//	public PostManager(SqlSession sqlSession, Recommender recommander) {
//		this.sqlSession = sqlSession;
//		this.recommander = recommander;
//	}

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

	public Response getPostsLists(int start, int id, int sort) {
		
		if(start<1 || id <1){
			return Response.arguemntError();
		}
		
		
		
		switch (sort) {
		case RECOMMENDATION_LISTS:
			return getRecommendedLists(id);
		case RECENT_LISTS:
			return getRecentlyLists(start, id);
		case POPULAR_LISTS:
			return getPopularLists(start, id);

		default:
			return getRecentlyLists(start, id);
		}
	}
	
	public Response getRecentlyLists(int start, int uid) {
		start = (start - 1) * 20;
		PostSel postSel = new PostSel(start, uid);
		
		Response response = Response.success();
		response.setTotal(getTotalRows());
		response.setData(sqlSession.selectList("PostMapper.selectRecentlyList", postSel));
		return response;
	}
	
	private Response getPopularLists(int start, int uid) {
		start = (start - 1) * 20;
		PostSel postSel = new PostSel(start, uid);
		
		Response response = Response.success();
		response.setTotal(getTotalRows());
		response.setData(sqlSession.selectList("PostMapper.selectPopularList", postSel));
		return response;
	}


	public Response getRecommendedLists(int id) {
		
		List<String> postIDList = getRecommendedPostIDList(id);
		if(postIDList.isEmpty()){
			return Response.resultZero();
		}else{
			Response response = Response.success();
			response.setTotal(postIDList.size());
			response.setData(sqlSession.selectList("PostMapper.selectRecommendedLists", postIDList));
			return response;
		}
	}

	public List<String> getRecommendedPostIDList(int id) {
		try {
			List<RecommendedItem> recommendations= recommander.recommend(id, 20);
			List<String> postIDList = new ArrayList<String>();
			for(RecommendedItem recommendation:recommendations){
				postIDList.add(String.valueOf(recommendation.getItemID()));
			}
			return postIDList;
		} catch (TasteException e) {
			LOG.debug("recommendations Fail!");
			return new ArrayList<String>();
		}
	}

}
