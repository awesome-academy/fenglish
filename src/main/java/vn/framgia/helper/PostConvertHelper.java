package vn.framgia.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import vn.framgia.bean.PostInfo;
import vn.framgia.model.Post;

public class PostConvertHelper {
	private static final Logger logger = Logger.getLogger(PostConvertHelper.class);

	public static PostInfo convertSinglePostToPostInfo(Post post) {

		try {
			PostInfo postInfo = new PostInfo();
			BeanUtils.copyProperties(postInfo, post);
			return postInfo;
		} catch (Exception e) {
			logger.error("Error in convert single post to post Info: " + e.getMessage());
			return null;
		}
	}

	public static List<PostInfo> convertListPostToListPostInfo(List<Post> Posts) {

		try {
			List<PostInfo> PostInfos = new ArrayList<PostInfo>();

			for (Post Post : Posts) {
				PostInfo PostInfo = convertSinglePostToPostInfo(Post);
				PostInfos.add(PostInfo);
			}
			return PostInfos;
		} catch (Exception e) {
			logger.error("Error in convert list Post to list Post Info: " + e.getMessage());
			return Collections.emptyList();
		}
	}

	public static void convertSinglePostInfoToPost(Post Post, PostInfo PostInfo) {

		try {
			BeanUtils.copyProperties(Post, PostInfo);
		} catch (Exception e) {
			logger.error("Error in convert single Post Info to Post:" + e.getMessage());
		}
	}

	public static List<Post> convertPostInfoToPost(List<PostInfo> PostInfos) {

		try {
			List<Post> Posts = new ArrayList<Post>();

			for (PostInfo PostInfo : PostInfos) {
				Post Post = new Post();
				convertSinglePostInfoToPost(Post, PostInfo);
				Posts.add(Post);
			}
			return Posts;
		} catch (Exception e) {
			logger.error("Error in convert list Post Info to list Post: " + e.getMessage());
			return Collections.emptyList();
		}
	}

	public static void copyValuePostInfoToPost(Post post, PostInfo postInfo) {
		try {
			postInfo.setDeleted(post.getDeleted());
			postInfo.setPostTags(post.getPostTags());
			BeanUtils.copyProperties(post, postInfo);
		} catch (Exception e) {
			logger.error("Error in convertPostInfo: " + e.getMessage());
		}
	}
}
