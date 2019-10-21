/** 
 * Project Name:Partenr-service 
 * File Name:getSourceFromWebUtil.java 
 * Package Name:com.wl.cn.business.admin.bookmanage 
 * Date:2016年4月26日下午4:14:03 
 * Copyright (c) 2016, 蔚蓝网 All Rights Reserved. 
 * 
 */
package com.org.blogs.util;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/** 
 * ClassName: getSourceFromWebUtil <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * date: 2016年4月26日 下午4:14:03 <br/> 
 * 
 * @author FW
 * @version  
 * @since JDK 1.7
 */
public class GetSourceFromWebUtil_ {
	private static final String p = "p";
	private static final String a = "a";
	private static final String div = "div";
	private static final String h = "h1";
	private static final String h2 = "h2";
	private static final String li = "li";
	private static final String ul = "ul";
	private static final String span = "span";
	private static final String textarea = "textarea";
	private static final String img = "img";
	private static final String del = "del";
	private static final String header = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.22 Safari/537.36";

	/**
	 * 
	 * @author FW 
	 * @param filter
	 * @param sou
	 * @param isbn
	 * @return 通过isbn获取数据
	 * @since JDK 1.7
	 */
	public static JSONArray getSourceFromWebByIsbn(String filter, String sou, String isbn) {
		String url = null;
		Document doc = null;
		Document document = null;
		Elements eles = null;
		Elements eles1 = null;
		String href = null;
		JSONArray ja = new JSONArray();
		// 当当
		if (sou.equals("1")) {
			url = "http://search.dangdang.com/?key=" + isbn + "&filter=0%7C0%7C0%7C0%7C0%7C1%7C0%7C0%7C0%7C0%7C";
			try {
				doc = Jsoup.connect(url).timeout(10000).get();
				eles = doc.select(p + "[class=name] " + a);
				for (Element element : eles) {
					JSONObject json = new JSONObject();
					href = element.attr("href");
					doc = Jsoup.connect(href).timeout(10000).header("User-Agent", header).get();
					// 获取商品基本信息
					// 获取商品名称
					eles = doc.select(div + "[class=name_info] " + h);
					if (eles != null && !eles.isEmpty()) {
						if (eles.text() != null) {
							String productName = eles.text().trim();
							if (productName != null) {
								json.put("productName", productName);
							}
						}
					}
					// 获取原价
					// eles = doc.select(div + "[class=price_info] " + div +
					// "[class=right] " + span + "[class=price_m]");
					eles = doc.select(div + "[class=price_m]");
					if (eles != null && !eles.isEmpty()) {
						if (eles.text() != null) {
							String price = eles.text().split(":")[0].trim().replaceAll("¥", "");
							if (price != null && price.length() > 0) {
								json.put("price", price);
							}
						}
					}
					// 获取ISBN
					eles = doc.select(div + "[class=t_box_left] " + li);
					if (eles != null && !eles.isEmpty()) {
						eles = eles.select(li + ":contains(国际标准书号ISBN：)");
						if (eles != null && !eles.isEmpty()) {
							String barCode = eles.text().replaceAll("[^0-9]", "");
							if (barCode != null && barCode.trim().length() > 0) {
								json.put("barCode", barCode);
							}
						}
					}
					// 获取 出版社 作者 出版时间
					eles = doc.select(span + "[class=t1]");
					// 获取作者
					if (eles != null && !eles.isEmpty()) {
						eles1 = eles.select(span + "[id=author]:contains(作者:)");
						if (eles1 != null && !eles1.isEmpty()) {
							String[] author = eles1.text().trim().split(":");
							if (author.length > 1) {
								String authorNames = author[1];
								if (authorNames != null && authorNames.length() > 0) {
									json.put("authorNames", authorNames);
								}
							}
						}
						// 获取厂商名称
						eles1 = eles.select(span + ":contains(出版社:)");
						if (eles1 != null && !eles1.isEmpty()) {
							String[] manufacturerNames = eles1.text().trim().split(":");
							if (manufacturerNames.length > 1) {
								String manufacturerName = manufacturerNames[1];
								if (manufacturerName != null && manufacturerName.length() > 0) {
									json.put("manufacturerName", manufacturerName);
								}
							}
						}
						// 获取出版时间
						eles1 = eles.select(span + ":contains(出版时间:)");
						if (eles1 != null && !eles1.isEmpty()) {
							String[] publishOns = eles1.text().trim().split(":");
							if (publishOns.length > 1) {
								String publishOn = publishOns[1];
								if (publishOn != null && publishOn.length() > 0) {
									json.put("publishOn", publishOn);
								}
							}
						}
					}
					// 获取商品扩展信息
					eles = doc.select(div + "[class=t_box_left] " + li);
					// 获取版别信息
					if (eles != null && !eles.isEmpty()) {
						eles1 = eles.select(li + ":contains(版 次：)");
						if (eles != null && !eles1.isEmpty()) {
							String edition = eles1.text().replaceAll("[^0-9]", "");
							if (edition != null) {
								json.put("edition", edition);
							}
						}
						// 获取页数
						eles1 = eles.select(li + ":contains(页 数：)");
						if (eles1 != null && !eles1.isEmpty()) {
							String pages = eles1.text().replaceAll("[^0-9]", "");
							if (pages != null && !pages.equals(0)) {
								json.put("pages", pages);
							}
						}
						// 获取字数
						eles1 = eles.select(li + ":contains(字 数：)");
						if (eles1 != null && !eles1.isEmpty()) {
							String wordNum = eles1.text().substring(eles1.text().lastIndexOf("：") + 1);
							if (wordNum != null && !wordNum.equals(0)) {
								json.put("wordNum", wordNum);
							}
						}
						// 获取开本
						eles1 = eles.select(li + ":contains(开 本：)");
						if (eles1 != null && !eles1.isEmpty()) {
							String[] bs = eles1.text().split("：");
							if (bs.length > 1) {
								String bookSize = bs[1];
								json.put("bookSize", bookSize);
							}
						}
						// 获取包装
						eles1 = eles.select(li + ":contains(包 装：)");
						if (eles1 != null && !eles1.isEmpty()) {
							String[] covers = eles1.text().split("：");
							if (covers.length > 1) {
								String cover = covers[1];
								json.put("cover", cover);
							}
						}
						// 获取isbn
						eles1 = eles.select(li + ":contains(国际标准书号ISBN：)");
						if (eles1 != null && !eles1.isEmpty()) {
							String ISBN = eles1.text().replaceAll("[^0-9]", "");
							if (ISBN != null && ISBN.trim().length() > 0) {
								json.put("isbn", ISBN);
							}
						} else {
							eles = doc.select(div + "[class=clearfix m_t6]:has(" + div + ":contains(ＩＳＢＮ)) " + div + "[class=show_info_right max_width]");
							if (eles != null && !eles.isEmpty()) {
								if (eles.text() != null && !eles.isEmpty()) {
									String barCode = eles.text().trim();
									if (barCode != null && barCode.length() > 0) {
										json.put("barCode", barCode);
										json.put("isbn", barCode);
									}
								}
							}
						}
					}
					// 获取商品描述信息
					String wId = href.substring(href.indexOf("com/") + 4, href.lastIndexOf(".html")).trim();
					Long goodsId = Long.parseLong(wId);
					String url1 = "http://detail.dangdang.com/?r=callback%2Fdetail&productId=" + goodsId + "&templateType=publish&describeMap=&shopId=0&categoryPath=01.03.33.01.00.00";
					Map<String, String> harss = new HashMap<String, String>();
					harss.put("X-Requested-With", "XMLHttpRequest");
					String results = HttpClientUtil.getClientInvoke(url1, harss, "gbk");
					String detailMes = "";
					if (results != null && results.trim().length() > 0) {
						JSONObject detail = JSONObject.parseObject(results);
						if (detail != null) {
							String detail1 = detail.getString("data");
							if (detail1 != null && detail1.trim().length() > 0) {
								detailMes = detail1;
								JSONObject jsona = JSONObject.parseObject(detailMes);
								String result = jsona.getString("html");
								if (result != null) {
									detailMes = result.replaceAll(filter, "").replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "");
								}
							}
						}
					}
					eles = doc.select(div + "[id=abstract] " + textarea);
					// 获取编辑推荐
					if (eles != null && !eles.isEmpty()) {
						String editorRecommend = eles.text().trim();
						if (editorRecommend != null && editorRecommend.length() > 0 && !editorRecommend.equals("暂时没有内容")) {
							editorRecommend = editorRecommend.replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "").replaceAll(filter, "");
							json.put("editorRecommend", editorRecommend);
						}
					} else {
						if (detailMes != null) {
							if (detailMes.contains("<div id=\"abstract\" class=\"section\"><div class=\"title\"><span>编辑推荐</span></div><div class=\"descrip\"><span id=\"abstract-all\"></span>")) {
								String editorRecommends = detailMes.substring(detailMes.indexOf("<div id=\"abstract\" class=\"section\"><div class=\"title\"><span>编辑推荐</span></div><div class=\"descrip\"><span id=\"abstract-all\"></span>"), detailMes.indexOf("</div></div>", detailMes.indexOf("<div id=\"abstract\" class=\"section\"><div class=\"title\"><span>编辑推荐</span></div><div class=\"descrip\"><span id=\"abstract-all\"></span>")));
								String editorRecommend = editorRecommends.replace("<div id=\"abstract\" class=\"section\"><div class=\"title\"><span>编辑推荐</span></div><div class=\"descrip\"><span id=\"abstract-all\"></span>", "");
								json.put("editorRecommend", editorRecommend);
							}
						}
					}
					// 获取简介
					eles = doc.select(div + "[id=content] " + textarea);
					if (eles != null && !eles.isEmpty()) {
						String description = eles.text().trim();
						if (description != null && description.length() > 0 && !description.equals("暂时没有内容")) {
							description = description.replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "").replaceAll(filter, "");
							json.put("description", description);
						}
					} else {
						if (detailMes != null) {
							if (detailMes.contains("<div id=\"content\" class=\"section\"><div class=\"title\"><span>内容推荐</span></div><div class=\"descrip\"><span id=\"content-all\"></span>")) {
								String descriptions = detailMes.substring(detailMes.indexOf("<div id=\"content\" class=\"section\"><div class=\"title\"><span>内容推荐</span></div><div class=\"descrip\"><span id=\"content-all\"></span>"), detailMes.indexOf("</div></div>", detailMes.indexOf("<div id=\"content\" class=\"section\"><div class=\"title\"><span>内容推荐</span></div><div class=\"descrip\"><span id=\"content-all\"></span>")));
								String description = descriptions.replace("<div id=\"content\" class=\"section\"><div class=\"title\"><span>内容推荐</span></div><div class=\"descrip\"><span id=\"content-all\"></span>", "");
								json.put("description", description);
							} else if (detailMes.contains("<div id=\"content\" class=\"section\"><div class=\"title\"><span>内容推荐</span></div><div class=\"descrip\"><span id=\"content-show\">")) {
								String descriptions = detailMes.substring(detailMes.indexOf("<div id=\"content\" class=\"section\"><div class=\"title\"><span>内容推荐</span></div><div class=\"descrip\"><span id=\"content-show\">"), detailMes.indexOf("</textarea>", detailMes.indexOf("<div id=\"content\" class=\"section\"><div class=\"title\"><span>内容推荐</span></div><div class=\"descrip\"><span id=\"content-show\">")));
								String description = descriptions.replace("<div id=\"content\" class=\"section\"><div class=\"title\"><span>内容推荐</span></div><div class=\"descrip\"><span id=\"content-show\">", "");
								json.put("description", description);
							}
						}
					}
					// 获取目录
					eles = doc.select(div + "[id=catalog] " + textarea);
					if (eles != null && !eles.isEmpty()) {
						String index = eles.text();
						if (index != null && index.length() > 0 && !index.equals("暂时没有内容")) {
							index = index.replaceAll(filter, "").replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "");// .replaceAll("&lt;p&gt;",
																																							// "<p>").replaceAll("&lt;/p&gt;",
																																							// "</p>");
							json.put("index", index);
						}
					} else {
						if (detailMes != null) {
							if (detailMes.contains("<span id=\"catalog-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"catalog-textarea\">")) {
								String indexs = detailMes.substring(detailMes.indexOf("<span id=\"catalog-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"catalog-textarea\">"), detailMes.indexOf("</textarea>", detailMes.indexOf("<span id=\"catalog-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"catalog-textarea\">")));
								String index = indexs.replace("<span id=\"catalog-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"catalog-textarea\">", "");
								json.put("index", index);
							} else if (detailMes.contains("<div id=\"catalog\" class=\"section\"><div class=\"title\"><span>目　　录</span></div><div class=\"descrip\"><span id=\"catalog-all\"></span>")) {
								String indexs = detailMes.substring(detailMes.indexOf("<div id=\"catalog\" class=\"section\"><div class=\"title\"><span>目　　录</span></div><div class=\"descrip\"><span id=\"catalog-all\"></span>"), detailMes.indexOf("</div></div>", detailMes.indexOf("<div id=\"catalog\" class=\"section\"><div class=\"title\"><span>目　　录</span></div><div class=\"descrip\"><span id=\"catalog-all\"></span>")));
								String index = indexs.replace("<div id=\"catalog\" class=\"section\"><div class=\"title\"><span>目　　录</span></div><div class=\"descrip\"><span id=\"catalog-all\"></span>", "");
								json.put("index", index);
							}
						}
					}
					// 获取前言
					eles = doc.select(div + "[id=preface] " + textarea);
					if (eles != null && !eles.isEmpty()) {
						String foreword = eles.text().trim();
						if (foreword != null && foreword.length() > 0 && !foreword.equals("暂时没有内容")) {
							foreword = foreword.replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "").replaceAll(filter, "");
							json.put("foreword", foreword);
						}
					} else {
						if (detailMes != null) {
							if (detailMes.contains("<span id=\"preface-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"preface-textarea\">")) {
								String forewords = detailMes.substring(detailMes.indexOf("<span id=\"preface-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"preface-textarea\">"), detailMes.indexOf("</textarea>", detailMes.indexOf("<span id=\"preface-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"preface-textarea\">")));
								String foreword = forewords.replace("<span id=\"preface-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"preface-textarea\">", "");
								json.put("foreword", foreword);
							}
						}
					}
					// 获取书评
					eles = doc.select(div + "[id=mediafeedback] " + textarea);
					if (eles != null && !eles.isEmpty()) {
						String bookReview = eles.text().trim();
						if (bookReview != null && bookReview.length() > 0 && !bookReview.equals("暂时没有内容")) {
							bookReview = bookReview.replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "").replaceAll(filter, "");
							json.put("bookReview", bookReview);
						}
					} else {
						if (detailMes != null) {
							if (detailMes.contains("<span id=\"mediaFeedback-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"mediaFeedback-textarea\">")) {
								String bookReviews = detailMes.substring(detailMes.indexOf("<span id=\"mediaFeedback-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"mediaFeedback-textarea\">"), detailMes.indexOf("</textarea>", detailMes.indexOf("<span id=\"mediaFeedback-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"mediaFeedback-textarea\">")));
								String bookReview = bookReviews.replace("<span id=\"mediaFeedback-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"mediaFeedback-textarea\">", "");
								json.put("bookReview", bookReview);
							} else if (detailMes.contains("<div id=\"mediaFeedback\" class=\"section\"><div class=\"title\"><span>媒体评论</span></div><div class=\"descrip\"><span id=\"mediaFeedback-all\"></span>")) {
								String bookReviews = detailMes.substring(detailMes.indexOf("<div id=\"mediaFeedback\" class=\"section\"><div class=\"title\"><span>媒体评论</span></div><div class=\"descrip\"><span id=\"mediaFeedback-all\"></span>"), detailMes.indexOf("</div></div>", detailMes.indexOf("<div id=\"mediaFeedback\" class=\"section\"><div class=\"title\"><span>媒体评论</span></div><div class=\"descrip\"><span id=\"mediaFeedback-all\"></span>")));
								String bookReview = bookReviews.replace("<div id=\"mediaFeedback\" class=\"section\"><div class=\"title\"><span>媒体评论</span></div><div class=\"descrip\"><span id=\"mediaFeedback-all\"></span>", "");
								json.put("bookReview", bookReview);
							}
						}
					}
					// 获取图片
					eles = doc.select(div + "[class=pic] " + img + "[id=largePic]");
					if (eles != null && !eles.isEmpty()) {
						String src = eles.attr("src");
						if (src != null && src.length() > 0) {
							src = src.replaceAll("_w", "_o");
							json.put("src", src);
						}
					}
					ja.add(json);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 从京东自营获取数据
		if (sou.equals("2")) {
			url = "http://search.jd.com/Search?keyword=" + isbn + "&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wtype=1&click=1";
			try {
				doc = Jsoup.connect(url).timeout(10000).get();
				eles = doc.select(div + "[class=p-name] " + a);
				for (Element element : eles) {
					JSONObject json = new JSONObject();
					href = element.attr("href");
					String srcs = href.replaceAll("//", "");
					if (srcs.startsWith("e.jd.com")) {
						continue;
					}
					if (href.indexOf("http:") == -1) {
						href = "http:" + href;
					}
					// 获取原价
					eles = doc.select(div + "[class=p-market] " + del);
					if (eles != null && !eles.isEmpty()) {
						if (eles.text() != null) {
							if (eles.get(0) != null) {
								String price = eles.get(0).text().trim().replaceAll("￥", "");
								if (price != null && price.length() > 0) {
									json.put("price", price);
								}
							}
						}
					}
					// 跳转到商品详情页面
					document = Jsoup.connect(href).timeout(10000).header("User-Agent", header).get();
					// 获取商品基本信息
					// 获取商品名称
					eles = document.select(div + "[id=name] " + h);
					if (eles != null && !eles.isEmpty()) {
						if (eles.text() != null) {
							String productName = eles.text().trim();
							if (productName != null) {
								json.put("productName", productName);
							}
						}
					}
					// 获取ISBN
					eles = document.select(div + "[class=p-parameter] " + li);
					if (eles != null && !eles.isEmpty()) {
						eles1 = eles.select(li + ":contains(ISBN：)");
						if (eles1 != null && !eles1.isEmpty()) {
							String barCode = eles1.text().replaceAll("[^0-9]", "");
							if (barCode != null && barCode.trim().length() > 0) {
								json.put("barCode", barCode);
								json.put("isbn", barCode);
							}
						}
						// 获取厂商名称
						eles1 = eles.select(li + ":contains(出版社： )");
						if (eles1 != null && !eles1.isEmpty()) {
							String manufacturerName = eles1.text().trim().split("： ")[1];
							if (manufacturerName != null && manufacturerName.length() > 0) {
								json.put("manufacturerName", manufacturerName);
							}
						}
						// 获取出版时间
						eles1 = eles.select(li + ":contains(出版时间：)");
						if (eles1 != null && !eles1.isEmpty()) {
							String publishOn = eles1.text().trim().split("：")[1];
							if (publishOn != null && publishOn.length() > 0) {
								json.put("publishOn", publishOn);
							}
						}
						// 获取版次
						eles1 = eles.select(li + ":contains(版次：)");
						if (eles1 != null && !eles1.isEmpty()) {
							String edition = eles1.text().trim().split("：")[1];
							if (edition != null && edition.length() > 0) {
								json.put("edition", edition);
							}
						}
						// 获取包装
						eles1 = eles.select(li + ":contains(包装：)");
						if (eles1 != null && !eles1.isEmpty()) {
							String cover = eles1.text().trim().split("：")[1];
							if (cover != null && cover.length() > 0) {
								json.put("cover", cover);
							}
						}
						// 获取开本
						eles1 = eles.select(li + ":contains(开本：)");
						if (eles1 != null && !eles1.isEmpty()) {
							String bookSize = eles1.text().trim().split("：")[1];
							if (bookSize != null && bookSize.length() > 0) {
								json.put("bookSize", bookSize);
							}
						}
						// 获取页数
						eles1 = eles.select(li + ":contains(页数：)");
						if (eles1 != null && !eles1.isEmpty()) {
							String pages = eles1.text().trim().split("：")[1];
							if (pages != null && pages.length() > 0) {
								json.put("pages", pages);
							}
						}
						// 获取字数
						eles1 = eles.select(li + ":contains(字数：)");
						if (eles1 != null && !eles1.isEmpty()) {
							String[] wordNums = eles1.text().trim().split("：");
							if (wordNums != null && wordNums.length > 0) {
								String wordNum = wordNums[1];
								json.put("wordNum", wordNum);
							}
						}
					}
					// 获取 作者
					eles = document.select(div + "[class=p-author] " + a);
					// 获取作者
					if (eles != null && !eles.isEmpty()) {
						String authorNames = eles.text().trim().split(" ")[0];
						if (authorNames != null && authorNames.length() > 0) {
							json.put("authorNames", authorNames);
						}
					}
					// 获取图片
					eles = document.select(div + "[class=jqzoom] " + img);
					if (eles != null && !eles.isEmpty()) {
						String src = eles.attr("src").replace("/n0", "/imgzone").replace("/n1", "/imgzone");
						if (src != null && !src.startsWith("http:")) {
							src = "http:" + src;
						}
						json.put("src", src);
					}
					// 获取描述信息
					String wareId = href.replaceAll("[^0-9]", "");
					String inforUrl = "http://d.3.cn/desc/" + wareId + "?cdn=2&callback=showdesc";
					Map<String, String> hars = new HashMap<String, String>();
					hars.put("X-Requested-With", "XMLHttpRequest");
					String result = HttpClientUtil.getClientInvoke(inforUrl, hars, "gbk");
					if (result != null) {
						String te = result.replaceAll("[showdesc]+" + "[(]+", " ").replaceAll("[)]+", " ");
						JSONObject jsons = JSONObject.parseObject(te);
						String content = jsons.getString("content");
						if (content != null) {
							// 获取编辑推荐
							if (content.contains("<div id=\"detail-tag-id-2\" name=\"detail-tag-id-2\" text=\"编辑推荐\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|bianjituijianqu_3\">\n")) {
								String editorRecommends = content.substring(content.indexOf("<div id=\"detail-tag-id-2\" name=\"detail-tag-id-2\" text=\"编辑推荐\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|bianjituijianqu_3\">\n"), content.indexOf("</div>\n        </div>\n    </div>\n", content.indexOf("<div id=\"detail-tag-id-2\" name=\"detail-tag-id-2\" text=\"编辑推荐\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|bianjituijianqu_3\">\n")));
								String editorRecommend = editorRecommends.substring(editorRecommends.indexOf("<div class=\"book-detail-content\">")).replace("<div class=\"book-detail-content\">", "").replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "").replaceAll(filter, "").trim();
								if (editorRecommend != null && editorRecommend.trim().length() > 0) {
									json.put("editorRecommend", editorRecommend);
								}
							}
							// 获取内容简介
							if (content.contains("<div id=\"detail-tag-id-3\" name=\"detail-tag-id-3\" text=\"内容简介\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|neirongjianjiequ_3\">\n")) {
								String descriptions = content.substring(content.indexOf("<div id=\"detail-tag-id-3\" name=\"detail-tag-id-3\" text=\"内容简介\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|neirongjianjiequ_3\">\n"), content.indexOf("</div>\n        </div>\n    </div>\n", content.indexOf("<div id=\"detail-tag-id-3\" name=\"detail-tag-id-3\" text=\"内容简介\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|neirongjianjiequ_3\">\n")));
								String description = descriptions.substring(descriptions.indexOf("<div class=\"book-detail-content\">")).replace("<div class=\"book-detail-content\">", "").replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "").replaceAll(filter, "").trim();
								if (description != null && description.trim().length() > 0) {
									json.put("description", description);
								}
							}
							// 获取书评
							if (content.contains("<div id=\"detail-tag-id-5\" name=\"detail-tag-id-5\" text=\"精彩书评\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|jingcaishupingqu_3\">\n")) {
								String bookReviews = content.substring(content.indexOf("<div id=\"detail-tag-id-5\" name=\"detail-tag-id-5\" text=\"精彩书评\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|jingcaishupingqu_3\">\n"), content.indexOf("</div>\n        </div>\n    </div>\n", content.indexOf("<div id=\"detail-tag-id-5\" name=\"detail-tag-id-5\" text=\"精彩书评\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|jingcaishupingqu_3\">\n")));
								String bookReview = bookReviews.substring(bookReviews.indexOf("<div class=\"book-detail-content\">")).replace("<div class=\"book-detail-content\">", "").replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "").replaceAll(filter, "").trim();
								if (bookReview != null && bookReview.trim().length() > 0) {
									json.put("bookReview", bookReview);
								}
							}
							// 获取目录
							if (content.contains("<div id=\"detail-tag-id-6\" name=\"detail-tag-id-6\" text=\"目录\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|muluqu_3\">\n")) {
								String indexs = content.substring(content.indexOf("<div id=\"detail-tag-id-6\" name=\"detail-tag-id-6\" text=\"目录\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|muluqu_3\">\n"), content.indexOf("</div>\n        </div>\n", content.indexOf("<div id=\"detail-tag-id-6\" name=\"detail-tag-id-6\" text=\"目录\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|muluqu_3\">\n")));
								if (indexs != null && indexs.trim().length() > 0) {
									String index = indexs.substring(indexs.indexOf("<div class=\"book-detail-content\">")).replace("<div class=\"book-detail-content\">", "").replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "").replaceAll(filter, "").trim();
									json.put("index", index);
								}
							}
							// 获取书摘
							if (content.contains("<div id=\"detail-tag-id-7\" name=\"detail-tag-id-7\" text=\"精彩书摘\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|jingcaishuzhaiqu_3\">\n")) {
								String summarys = content.substring(content.indexOf("<div id=\"detail-tag-id-7\" name=\"detail-tag-id-7\" text=\"精彩书摘\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|jingcaishuzhaiqu_3\">\n"), content.indexOf("</div>\n        </div>\n", content.indexOf("<div id=\"detail-tag-id-7\" name=\"detail-tag-id-7\" text=\"精彩书摘\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|jingcaishuzhaiqu_3\">\n")));
								if (summarys != null && summarys.trim().length() > 0) {
									String summary = summarys.substring(summarys.indexOf("<div class=\"book-detail-content\">")).replace("<div class=\"book-detail-content\">", "").replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "").replaceAll(filter, "").trim();
									json.put("summary", summary);
								}
							}
							// 获取前言
							if (content.contains("<div id=\"detail-tag-id-8\" name=\"detail-tag-id-8\" text=\"前言/序言\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|qianyanqu_3\">\n")) {
								String forewords = content.substring(content.indexOf("<div id=\"detail-tag-id-8\" name=\"detail-tag-id-8\" text=\"前言/序言\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|qianyanqu_3\">\n "), content.indexOf("</div>\n        </div>\n", content.indexOf("<div id=\"detail-tag-id-8\" name=\"detail-tag-id-8\" text=\"前言/序言\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|qianyanqu_3\">\n ")));
								if (forewords != null && forewords.trim().length() > 0) {
									String foreword = forewords.substring(forewords.indexOf("<div class=\"book-detail-content\">")).replace("<div class=\"book-detail-content\">", "").replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "").replaceAll(filter, "").trim();
									if (!foreword.equals("<p></p>") && foreword != null) {
										json.put("foreword", foreword);
									}
								}
							}
						}
					}
					ja.add(json);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 从天猫获取数据
		if (sou.equals("3")) {
			url = "https://list.tmall.com/search_product.htm?q=" + isbn;
			String src = null;
			try {
				doc = Jsoup.connect(url).timeout(10000).get();
				eles = doc.select(p + "[class=productTitle] " + a);
				for (Element element : eles) {
					JSONObject json = new JSONObject();
					String src1 = element.attr("href");
					if (src1 != null && src1.trim().length() > 0 && !src1.startsWith("https")) {
						src = "https:" + src1;
					}
					if (src != null && src.trim().length() > 0) {
						document = Jsoup.connect(src).timeout(10000).header("User-Agent", header).get();
						// 获取基本信息
						// 获取商品名称
						eles = document.select(div + "[class=tb-detail-hd] " + h);
						if (eles != null) {
							String productName = eles.text().trim();
							if (productName != null && productName.trim().length() > 0) {
								json.put("productName", productName);
							}
						}
						eles = document.select(ul + "[id=J_AttrUL] " + li);
						// 获取商品原价
						eles1 = eles.select(li + ":contains(定价:)");
						if (eles1 != null && !eles1.isEmpty()) {
							String[] prices = eles1.text().trim().split(":");
							if (prices != null && prices.length > 1) {
								String price = prices[1];
								json.put("price", price);
							}
						}
						// 获取isbn
						eles1 = eles.select(li + ":contains(ISBN编号:)");
						if (eles1 != null && !eles1.isEmpty()) {
							String[] isbns = eles1.text().split(": ");
							if (isbns != null && isbns.length > 1) {
								String barCode = isbns[1];
								json.put("barCode", barCode);
								json.put("isbn", barCode);
							}
						}
						// 获取作者
						eles1 = eles.select(li + ":contains(作者:)");
						if (eles1 != null && !eles1.isEmpty()) {
							String[] authorNamess = eles1.text().split(": ");
							if (authorNamess.length > 1) {
								String authorNames = authorNamess[1];
								json.put("authorNames", authorNames);
							}
						}
						// 获取厂商
						eles1 = eles.select(li + ":contains(出版社名称:)");
						if (eles1 != null && !eles1.isEmpty()) {
							String[] manufacturerNames = eles1.text().split(": ");
							if (manufacturerNames.length > 1) {
								String manufacturerName = manufacturerNames[1];
								json.put("manufacturerName", manufacturerName);
							}
						}
						// 获取出版时间
						eles1 = eles.select(li + ":contains(出版时间:)");
						if (eles1 != null && !eles1.isEmpty()) {
							String[] publishOns = eles1.text().split(": ");
							if (publishOns.length > 1) {
								String publishOn = publishOns[1];
								json.put("publishOn", publishOn);
							}
						}
						// 获取扩展属性
						// 获取开本
						eles1 = eles.select(li + ":contains(出版时间:)");
						if (eles1 != null && !eles1.isEmpty()) {
							String[] bookSizes = eles1.text().split(": ");
							if (bookSizes.length > 1) {
								String bookSize = bookSizes[1];
								json.put("bookSize", bookSize);
							}
						}
						// 抓取图片
						eles = document.select(img + "[id=J_ImgBooth]");
						if (eles != null && !eles.isEmpty()) {
							src1 = eles.attr("src");
							String pictureType = src1.substring(src1.lastIndexOf("."), src1.length());
							src = src1.substring(0, src1.indexOf(pictureType, 0)) + pictureType;
							if (src != null && !src.startsWith("https:")) {
								src = "https:" + src;
							}
							json.put("src", src);
						}
						// 获取描述信息
						/*
						 * String strDoc = document.toString(); String[]
						 * descUrls =
						 * strDoc.substring(strDoc.indexOf("httpsDescUrl"),
						 * strDoc.indexOf("}",
						 * strDoc.indexOf("httpsDescUrl"))).split(":"); if
						 * (descUrls != null && descUrls.length > 1) { descUrl =
						 * descUrls[1].replaceAll("[\"]*", ""); if (descUrl !=
						 * null && !descUrl.startsWith("https")) { descUrl =
						 * "https:" + descUrl; } document =
						 * Jsoup.connect(descUrl
						 * ).timeout(10000).header("User-Agent", header).get();
						 * // 获取编辑推荐 eles = document.select(table + ":has(" + td
						 * + ":contains(主编推荐))"); if (eles != null &&
						 * !eles.isEmpty()) { eles = eles.select(td); if
						 * (eles.size() > 1) { String editorRecommend =
						 * eles.get(1).text().trim(); if (editorRecommend !=
						 * null && editorRecommend.trim().length() > 0) {
						 * json.put("editorRecommend", editorRecommend); } } }
						 * // 获取书评 eles = document.select(table + ":has(" + td +
						 * ":contains(媒体评论))"); if (eles != null &&
						 * !eles.isEmpty()) { eles = eles.select(td); if
						 * (eles.size() > 1) { String bookReview =
						 * eles.get(1).text().trim(); if (bookReview != null &&
						 * bookReview.trim().length() > 0) {
						 * json.put("bookReview", bookReview); } } } // 获取目录
						 * eles = document.select(table + ":has(" + td +
						 * ":contains(目录))"); if (eles != null &&
						 * !eles.isEmpty()) { eles = eles.select(td); if
						 * (eles.size() > 1) { String index =
						 * eles.get(1).text().trim(); if (index != null &&
						 * index.trim().length() > 0) { json.put("index",
						 * index); } } } // 获取内容简介 eles = document.select(table
						 * + ":has(" + td + ":contains(内容简介))"); if (eles !=
						 * null && !eles.isEmpty()) { eles = eles.select(tr +
						 * td); if (eles.size() > 1) { String description =
						 * eles.get(1).text().trim(); if (description != null &&
						 * description.trim().length() > 0) {
						 * json.put("description", description); } } } }
						 */
					}
					ja.add(json);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ja;
	}

	/**
	 * 通过url获取数据
	 */
	public static JSONArray getSourceFromWebByUrl(String filter, String sou, String url) {
		Document doc = null;
		Elements eles = null;
		Elements eles1 = null;
		JSONObject json = new JSONObject();
		JSONArray ja = new JSONArray();

		if (sou.equals("dangdang")) {
			try {
				doc = Jsoup.connect(url).timeout(10000).header("User-Agent", header).get();
				// 获取商品基本信息
				// 获取商品名称
				eles = doc.select(div + "[class=name_info] " + h);
				if (eles != null && !eles.isEmpty()) {
					if (eles.text() != null) {
						String productName = eles.text().trim();
						if (productName != null) {
							json.put("productName", productName);
						}
					}
				} else {
					eles = doc.select(div + "[class=head] " + h);
					if (eles.text() != null) {
						String productName = eles.text().trim();
						if (productName != null) {
							json.put("productName", productName);
						}
					}
				}
				// 获取原价
				// eles = doc.select(div + "[class=price_info] " + div +
				// "[class=right] " + span + "[class=price_m]");
				eles = doc.select(div + "[class=price_m]");
				if (eles != null && !eles.isEmpty()) {
					if (eles.text() != null) {
						String price = eles.text().split(":")[0].trim().replaceAll("¥", "");
						if (price != null && price.length() > 0) {
							json.put("price", price);
						}
					}
				} else {
					eles = doc.select(span + "[id=originalPriceTag]");
					if (eles.text() != null) {
						String price = eles.text().split(":")[0].trim().replaceAll("¥", "");
						if (price != null && price.length() > 0) {
							json.put("price", price);
						}
					}
				}
				// 获取ISBN
				eles = doc.select(div + "[class=t_box_left] " + li);
				if (eles != null && !eles.isEmpty()) {
					eles = eles.select(li + ":contains(国际标准书号ISBN：)");
					if (eles != null) {
						String barCode = eles.text().replaceAll("[^0-9]", "");
						if (barCode != null && barCode.trim().length() > 0) {
							json.put("barCode", barCode);
							json.put("isbn", barCode);
						}
					}
				} else {
					eles = doc.select(div + "[class=clearfix m_t6]:has(" + div + ":contains(ＩＳＢＮ)) " + div + "[class=show_info_right max_width]");
					if (eles != null && !eles.isEmpty()) {
						if (eles.text() != null && !eles.isEmpty()) {
							String barCode = eles.text().trim();
							if (barCode != null && barCode.length() > 0) {
								json.put("barCode", barCode);
								json.put("isbn", barCode);
							}
						}
					}
				}
				// 获取 出版社 作者 出版时间
				eles = doc.select(span + "[class=t1]");
				// 获取作者
				if (eles != null && !eles.isEmpty()) {
					eles1 = eles.select(span + "[id=author]:contains(作者:)");
					if (eles1 != null && !eles1.isEmpty()) {
						String[] author = eles1.text().trim().split(":");
						if (author.length > 1) {
							String authorNames = author[1];
							if (authorNames != null && authorNames.length() > 0) {
								json.put("authorNames", authorNames);
							}
						}
					}
					// 获取厂商名称
					eles1 = eles.select(span + ":contains(出版社:)");
					if (eles1 != null && !eles1.isEmpty()) {
						String[] manufacturerNames = eles1.text().trim().split(":");
						if (manufacturerNames.length > 1) {
							String manufacturerName = manufacturerNames[1];
							if (manufacturerName != null && manufacturerName.length() > 0) {
								json.put("manufacturerName", manufacturerName);
							}
						}
					}
					// 获取出版时间
					eles1 = eles.select(span + ":contains(出版时间:)");
					if (eles1 != null && !eles1.isEmpty()) {
						String[] publishOns = eles1.text().trim().split(":");
						if (publishOns.length > 1) {
							String publishOn = publishOns[1];
							if (publishOn != null && publishOn.length() > 0) {
								json.put("publishOn", publishOn);
							}
						}
					}
				} else {
					// 获取作者
					eles = doc.select(div + "[class=clearfix m_t6]:has(" + div + ":contains(作)) " + div + "[class=show_info_right max_width] " + a);
					if (eles != null && !eles.isEmpty()) {
						String authorNames = eles.text().trim().split(":")[0];
						if (authorNames != null && authorNames.length() > 0) {
							json.put("authorNames", authorNames);
						}
					}
					// 获取厂商名称
					eles = doc.select(div + "[class=clearfix m_t6]:has(" + div + ":contains(出)) " + div + "[class=show_info_right max_width] " + a);
					if (eles != null && !eles.isEmpty()) {
						String manufacturerName = eles.text().trim();
						if (manufacturerName != null && manufacturerName.length() > 0) {
							json.put("manufacturerName", manufacturerName);
						}
					}
					// 获取的出版时间
					eles = doc.select(div + "[class=clearfix m_t6]:has(" + div + ":contains(出版时间)) " + div + "[class=show_info_right max_width]");
					if (eles != null && !eles.isEmpty()) {
						String publishOn = eles.text().trim();
						if (publishOn != null && publishOn.length() > 0) {
							json.put("publishOn", publishOn);
						}
					}
				}
				// 获取商品扩展信息
				eles = doc.select(div + "[class=t_box_left] " + li);
				// 获取版别信息
				if (eles != null && !eles.isEmpty()) {
					eles1 = eles.select(li + ":contains(版 次：)");
					if (eles != null && !eles.isEmpty()) {
						String edition = eles1.text().replaceAll("[^0-9]", "");
						if (edition != null) {
							json.put("edition", edition);
						}
					}
					// 获取页数
					eles1 = eles.select(li + ":contains(页 数：)");
					if (eles1 != null && !eles1.isEmpty()) {
						String pages = eles1.text().replaceAll("[^0-9]", "");
						if (pages != null && !pages.equals(0)) {
							json.put("pages", pages);
						}
					}
					// 获取字数
					eles1 = eles.select(li + ":contains(字 数：)");
					if (eles1 != null && !eles1.isEmpty()) {
						String wordNum = eles1.text().substring(eles1.text().lastIndexOf("：") + 1);
						if (wordNum != null && !wordNum.equals(0)) {
							json.put("wordNum", wordNum);
						}
					}
					// 获取开本
					eles1 = eles.select(li + ":contains(开 本：)");
					if (eles1 != null && !eles1.isEmpty()) {
						String[] bs = eles1.text().split("：");
						if (bs.length > 1) {
							String bookSize = bs[1];
							if (bookSize != null) {
								json.put("bookSize", bookSize);

							}
						}
					}
					// 获取包装
					eles1 = eles.select(li + ":contains(包 装：)");
					if (eles1 != null && !eles1.isEmpty()) {
						String[] covers = eles1.text().split("：");
						if (covers.length > 1) {
							String cover = covers[1];
							if (cover != null) {
								json.put("cover", cover);
							}
						}
					}
				} else {
					// 获取商品扩展信息
					eles = doc.select(div + "[ class=pro_content] " + ul + "[class=key clearfix] " + li);
					// 获取版别信息
					if (eles != null && !eles.isEmpty()) {
						eles1 = eles.select(li + ":contains(版 次：)");
						if (eles1 != null && !eles1.isEmpty()) {
							String edition = eles1.text().replaceAll("[^0-9]", "");
							if (edition != null) {
								json.put("edition", edition);
							}
						}
						// 获取页数
						eles1 = eles.select(li + ":contains(页 数：)");
						if (eles1 != null && !eles1.isEmpty()) {
							String pages = eles1.text().replaceAll("[^0-9]", "");
							if (pages != null && !pages.equals(0)) {
								json.put("pages", pages);
							}
						}
						// 获取字数
						eles1 = eles.select(li + ":contains(字 数：)");
						if (eles1 != null && !eles1.isEmpty()) {
							String wordNum = eles1.text().substring(eles1.text().lastIndexOf("：") + 1);
							if (wordNum != null && !wordNum.equals(0)) {
								json.put("wordNum", wordNum);
							}
						}
						// 获取开本
						eles1 = eles.select(li + ":contains(开 本：)");
						if (eles1 != null && !eles1.isEmpty()) {
							String[] bookSizes = eles1.text().split("：");
							if (bookSizes.length > 1) {
								String bookSize = bookSizes[1];
								if (bookSize != null && bookSize.trim().length() > 0) {
									json.put("bookSize", bookSize);
								}
							}
						}
						// 获取包装
						eles1 = eles.select(li + ":contains(包 装：)");
						if (eles1 != null && !eles1.isEmpty()) {
							String[] covers = eles1.text().split("：");
							if (covers.length > 1) {
								String cover = covers[1];
								if (cover != null) {
									json.put("cover", cover);
								}
							}
						}
					}
				}
				// 获取商品描述信息
				String wId = url.substring(url.indexOf("com/") + 4, url.lastIndexOf(".html")).trim();
				Long goodsId = Long.parseLong(wId);
				String url1 = "http://detail.dangdang.com/?r=callback%2Fdetail&productId=" + goodsId + "&templateType=publish&describeMap=&shopId=0&categoryPath=01.03.33.01.00.00";
				Map<String, String> harss = new HashMap<String, String>();
				harss.put("X-Requested-With", "XMLHttpRequest");
				String results = HttpClientUtil.getClientInvoke(url1, harss, "gbk");
				String detailMes = "";
				if (results != null && results.trim().length() > 0) {
					JSONObject detail = JSONObject.parseObject(results);
					if (detail != null) {
						String detail1 = detail.getString("data");
						if (detail1 != null && detail1.trim().length() > 0) {
							detailMes = detail1;
							JSONObject jsona = JSONObject.parseObject(detailMes);
							String result = jsona.getString("html");
							if (result != null) {
								detailMes = result.replaceAll(filter, "").replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "");
							} 
						}
					}
				}
				eles = doc.select(div + "[id=abstract] " + textarea);
				// 获取编辑推荐
				if (eles != null && !eles.isEmpty()) {
					String editorRecommend = eles.text().trim();
					if (editorRecommend != null && editorRecommend.length() > 0 && !editorRecommend.equals("暂时没有内容")) {
						editorRecommend = editorRecommend.replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "").replaceAll(filter, "");
						json.put("editorRecommend", editorRecommend);
					}
				} else {
					if (detailMes != null) {
						if (detailMes.contains("<div id=\"abstract\" class=\"section\"><div class=\"title\"><span>编辑推荐</span></div><div class=\"descrip\"><span id=\"abstract-all\"></span>")) {
							String editorRecommends = detailMes.substring(detailMes.indexOf("<div id=\"abstract\" class=\"section\"><div class=\"title\"><span>编辑推荐</span></div><div class=\"descrip\"><span id=\"abstract-all\"></span>"), detailMes.indexOf("</div></div>", detailMes.indexOf("<div id=\"abstract\" class=\"section\"><div class=\"title\"><span>编辑推荐</span></div><div class=\"descrip\"><span id=\"abstract-all\"></span>")));
							String editorRecommend = editorRecommends.replace("<div id=\"abstract\" class=\"section\"><div class=\"title\"><span>编辑推荐</span></div><div class=\"descrip\"><span id=\"abstract-all\"></span id=\"mediaFeedback-all\"></span>", "");
							json.put("editorRecommend", editorRecommend);
						}
					}
				}
				// 获取简介
				eles = doc.select(div + "[id=content] " + textarea);
				if (eles != null && !eles.isEmpty()) {
					String description = eles.text().trim();
					if (description != null && description.length() > 0 && !description.equals("暂时没有内容")) {
						description = description.replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "").replaceAll(filter, "");
						json.put("description", description);
					}
				} else {
					if (detailMes != null) {
						if (detailMes.contains("<div id=\"content\" class=\"section\"><div class=\"title\"><span>内容推荐</span></div><div class=\"descrip\"><span id=\"content-all\"></span>")) {
							String descriptions = detailMes.substring(detailMes.indexOf("<div id=\"content\" class=\"section\"><div class=\"title\"><span>内容推荐</span></div><div class=\"descrip\"><span id=\"content-all\"></span>"), detailMes.indexOf("</div></div>", detailMes.indexOf("<div id=\"content\" class=\"section\"><div class=\"title\"><span>内容推荐</span></div><div class=\"descrip\"><span id=\"content-all\"></span>")));
							String description = descriptions.replace("<div id=\"content\" class=\"section\"><div class=\"title\"><span>内容推荐</span></div><div class=\"descrip\"><span id=\"content-all\"></span>", "");
							json.put("description", description);
						} else if (detailMes.contains("<div id=\"content\" class=\"section\"><div class=\"title\"><span>内容推荐</span></div><div class=\"descrip\"><span id=\"content-show\">")) {
							String descriptions = detailMes.substring(detailMes.indexOf("<div id=\"content\" class=\"section\"><div class=\"title\"><span>内容推荐</span></div><div class=\"descrip\"><span id=\"content-show\">"), detailMes.indexOf("</div></div>", detailMes.indexOf("<div id=\"content\" class=\"section\"><div class=\"title\"><span>内容推荐</span></div><div class=\"descrip\"><span id=\"content-show\">")));
							String description = descriptions.replace("<div id=\"content\" class=\"section\"><div class=\"title\"><span>内容推荐</span></div><div class=\"descrip\"><span id=\"content-show\">", "");
							json.put("description", description);
						}/*
						 * else if(detailMes.contains(
						 * "<span id=\"content-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"content-textarea\">"
						 * )){ String descriptions =
						 * detailMes.substring(detailMes.indexOf(
						 * "<span id=\"content-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"content-textarea\">"
						 * ), detailMes.indexOf("</div></div>",
						 * detailMes.indexOf(
						 * "<span id=\"content-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"content-textarea\">"
						 * ))); String description = descriptions.replace(
						 * "<div id=\"content\" class=\"section\"><div class=\"title\"><span>内容推荐</span></div><div class=\"descrip\"><span id=\"content-show\">"
						 * , ""); json.put("description", description); }
						 */
					}
				}
				// 获取目录
				eles = doc.select(div + "[id=catalog] " + textarea);
				if (eles != null && !eles.isEmpty()) {
					String index = eles.text().trim();
					if (index != null && index.length() > 0 && !index.equals("暂时没有内容")) {
						index = index.replaceAll(filter, "").replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "");
						json.put("index", index);
					}
				} else {
					if (detailMes != null) {
						if (detailMes.contains("<span id=\"catalog-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"catalog-textarea\">")) {
							String indexs = detailMes.substring(detailMes.indexOf("<span id=\"catalog-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"catalog-textarea\">"), detailMes.indexOf("</textarea>", detailMes.indexOf("<span id=\"catalog-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"catalog-textarea\">")));
							String index = indexs.replace("<span id=\"catalog-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"catalog-textarea\">", "");
							json.put("index", index);
						} else if (detailMes.contains("<div id=\"catalog\" class=\"section\"><div class=\"title\"><span>目　　录</span></div><div class=\"descrip\"><span id=\"catalog-all\"></span>")) {
							String indexs = detailMes.substring(detailMes.indexOf("<div id=\"catalog\" class=\"section\"><div class=\"title\"><span>目　　录</span></div><div class=\"descrip\"><span id=\"catalog-all\"></span>"), detailMes.indexOf("</div></div>", detailMes.indexOf("<div id=\"catalog\" class=\"section\"><div class=\"title\"><span>目　　录</span></div><div class=\"descrip\"><span id=\"catalog-all\"></span>")));
							String index = indexs.replace("<div id=\"catalog\" class=\"section\"><div class=\"title\"><span>目　　录</span></div><div class=\"descrip\"><span id=\"catalog-all\"></span>", "");
							json.put("index", index);
						}
					}
				}
				// 获取前言
				eles = doc.select(div + "[id=preface] " + textarea);
				if (eles != null && !eles.isEmpty()) {
					String foreword = eles.text().trim();
					if (foreword != null && foreword.length() > 0 && !foreword.equals("暂时没有内容")) {
						foreword = foreword.replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "").replaceAll(filter, "");
						json.put("foreword", foreword);
					}
				} else {
					if (detailMes != null) {
						if (detailMes.contains("<span id=\"preface-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"preface-textarea\">")) {
							String forewords = detailMes.substring(detailMes.indexOf("<span id=\"preface-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"preface-textarea\">"), detailMes.indexOf("</textarea>", detailMes.indexOf("<span id=\"preface-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"preface-textarea\">")));
							String foreword = forewords.replace("<span id=\"preface-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"preface-textarea\">", "");
							json.put("foreword", foreword);
						}
					}
				}
				// 获取书评
				eles = doc.select(div + "[id=mediafeedback] " + textarea);
				if (eles != null && !eles.isEmpty()) {
					String bookReview = eles.text().trim();
					if (bookReview != null && bookReview.length() > 0 && !bookReview.equals("暂时没有内容")) {
						bookReview = bookReview.replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "").replaceAll(filter, "");
						json.put("bookReview", bookReview);
					}
				} else {
					if (detailMes != null) {
						if (detailMes.contains("<span id=\"mediaFeedback-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"mediaFeedback-textarea\">")) {
							String bookReviews = detailMes.substring(detailMes.indexOf("<span id=\"mediaFeedback-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"mediaFeedback-textarea\">"), detailMes.indexOf("</textarea>", detailMes.indexOf("<span id=\"mediaFeedback-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"mediaFeedback-textarea\">")));
							String bookReview = bookReviews.replace("<span id=\"mediaFeedback-show-all\" style=\"display:none;\"></span><textarea style=\"display:none\" id = \"mediaFeedback-textarea\">", "");
							json.put("bookReview", bookReview);
						} else if (detailMes.contains("<div id=\"mediaFeedback\" class=\"section\"><div class=\"title\"><span>媒体评论</span></div><div class=\"descrip\"><span id=\"mediaFeedback-all\"></span>")) {
							String bookReviews = detailMes.substring(detailMes.indexOf("<div id=\"mediaFeedback\" class=\"section\"><div class=\"title\"><span>媒体评论</span></div><div class=\"descrip\"><span id=\"mediaFeedback-all\"></span>"), detailMes.indexOf("</div></div>", detailMes.indexOf("<div id=\"mediaFeedback\" class=\"section\"><div class=\"title\"><span>媒体评论</span></div><div class=\"descrip\"><span id=\"mediaFeedback-all\"></span>")));
							String bookReview = bookReviews.replace("<div id=\"mediaFeedback\" class=\"section\"><div class=\"title\"><span>媒体评论</span></div><div class=\"descrip\"><span id=\"mediaFeedback-all\"></span>", "");
							json.put("bookReview", bookReview);
						}
					}
				}
				// 获取图片
				eles = doc.select(div + "[class=pic] " + img + "[id=largePic]");
				if (eles != null && !eles.isEmpty()) {
					String src = eles.attr("src");
					if (src != null && src.length() > 0 && src.startsWith("http://")) {
						src = src.replaceAll("_w_", "_o_");
						json.put("src", src);
					} else {
						String src1 = eles.attr("wsrc");
						if (src1 != null && src1.length() > 0 && src1.startsWith("http://")) {
							src1 = src1.replaceAll("_w_", "_o_");
							json.put("src", src1);
						}
					}
				}
				ja.add(json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (sou.equals("jd")) {
			try {

				String wId = url.substring(url.indexOf("com/") + 4, url.lastIndexOf(".html")).trim();
				Long goodsId = Long.parseLong(wId);
				String url1 = "https://p.3.cn/prices/get?type=1&area=1_72_2799&pdtk=&pduid=587231331&pdbp=0&skuid=J_" + goodsId + "&callback=cnp";
				Map<String, String> harss = new HashMap<String, String>();
				harss.put("X-Requested-With", "XMLHttpRequest");
				String results = HttpClientUtil.getClientInvoke(url1, harss, "gbk");
				if (results != null && results.trim().length() > 0) {
					results = results.substring(results.indexOf("cnp(") + 4, results.indexOf(")"));
					JSONArray pArray = JSONArray.parseArray(results);
					JSONObject pJson = (JSONObject) pArray.get(0);
					String price = pJson.getString("m");
					json.put("price", price);
				}
				doc = Jsoup.connect(url).timeout(10000).header("User-Agent", header).get();
				eles = doc.select(div + "[id=name] " + h);
				if (eles != null && !eles.isEmpty()) {
					if (eles.text() != null) {
						String productName = eles.text().trim();
						if (productName != null) {
							json.put("productName", productName);
						}
					}
				}
				// 获取ISBN
				eles = doc.select(div + "[class=p-parameter] " + li);
				if (eles != null && !eles.isEmpty()) {
					eles1 = eles.select(li + ":contains(ISBN：)");
					if (eles1 != null && !eles1.isEmpty()) {
						String barCode = eles1.text().replaceAll("[^0-9]", "");
						if (barCode != null && barCode.trim().length() > 0) {
							json.put("barCode", barCode);
							json.put("isbn", barCode);
						}
					}
					// 获取厂商名称
					eles1 = eles.select(li + ":contains(出版社： )");
					if (eles1 != null && !eles1.isEmpty()) {
						String manufacturerName = eles1.text().trim().split("： ")[1];
						if (manufacturerName != null && manufacturerName.length() > 0) {
							json.put("manufacturerName", manufacturerName);
						}
					}
					// 获取出版时间
					eles1 = eles.select(li + ":contains(出版时间：)");
					if (eles1 != null && !eles1.isEmpty()) {
						String publishOn = eles1.text().trim().split("：")[1];
						if (publishOn != null && publishOn.length() > 0) {
							json.put("publishOn", publishOn);
						}
					}
					// 获取版次
					eles1 = eles.select(li + ":contains(版次：)");
					if (eles1 != null && !eles1.isEmpty()) {
						String edition = eles1.text().trim().split("：")[1];
						if (edition != null && edition.length() > 0) {
							json.put("edition", edition);
						}
					}
					// 获取包装
					eles1 = eles.select(li + ":contains(包装：)");
					if (eles1 != null && !eles1.isEmpty()) {
						String cover = eles1.text().trim().split("：")[1];
						if (cover != null && cover.length() > 0) {
							json.put("cover", cover);
						}
					}
					// 获取开本
					eles1 = eles.select(li + ":contains(开本：)");
					if (eles1 != null && !eles1.isEmpty()) {
						String bookSize = eles1.text().trim().split("：")[1];
						if (bookSize != null && bookSize.length() > 0) {
							json.put("bookSize", bookSize);
						}
					}
					// 获取页数
					eles1 = eles.select(li + ":contains(页数：)");
					if (eles1 != null && !eles1.isEmpty()) {
						String pages = eles1.text().trim().split("：")[1];
						if (pages != null && pages.length() > 0) {
							json.put("pages", pages);
						}
					}
					// 获取字数
					eles1 = eles.select(li + ":contains(字数：)");
					if (eles1 != null && !eles1.isEmpty()) {
						String wordNum = eles1.text().trim().split("：")[1];
						if (wordNum != null && wordNum.length() > 0) {
							json.put("wordNum", wordNum);
						}
					}
				}
				// 获取 作者
				eles = doc.select(div + "[class=p-author] " + a);
				// 获取作者
				if (eles != null && !eles.isEmpty()) {
					String authorNames = eles.text().trim().split(" ")[0];
					if (authorNames != null && authorNames.length() > 0) {
						json.put("authorNames", authorNames);
					}
				}
				// 获取图片
				eles = doc.select(div + "[class=jqzoom] " + img);
				if (eles != null && !eles.isEmpty()) {
					String src = eles.attr("src").replace("/n0", "/imgzone").replace("/n1", "/imgzone");
					if (src != null && !src.startsWith("http:")) {
						src = "http:" + src;
					}
					json.put("src", src);
				}
				// 获取描述信息
				String scri = doc.toString();
				if (scri != null) {
					String wareIds = scri.substring(scri.indexOf("qualityLife:"), scri.indexOf("colorSize:"));
					String wareId = wareIds.substring(wareIds.indexOf("pid="), wareIds.indexOf("&catId")).replaceAll("[^0-9]", "");
					String inforUrl = "http://d.3.cn/desc/" + wareId + "?cdn=2&callback=showdesc";
					Map<String, String> hars = new HashMap<String, String>();
					hars.put("X-Requested-With", "XMLHttpRequest");
					String result = HttpClientUtil.getClientInvoke(inforUrl, hars, "gbk");
					if (result != null) {
						String te = result.replaceAll("[showdesc]+" + "[(]+", " ").replaceAll("[)]+", " ");
						JSONObject jsons = JSONObject.parseObject(te);
						String content = jsons.getString("content");
						if (content != null) {
							// 获取编辑推荐
							if (content.contains("<div id=\"detail-tag-id-2\" name=\"detail-tag-id-2\" text=\"编辑推荐\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|bianjituijianqu_3\">\n")) {
								String editorRecommends = content.substring(content.indexOf("<div id=\"detail-tag-id-2\" name=\"detail-tag-id-2\" text=\"编辑推荐\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|bianjituijianqu_3\">\n"), content.indexOf("</div>\n        </div>\n    </div>\n", content.indexOf("<div id=\"detail-tag-id-2\" name=\"detail-tag-id-2\" text=\"编辑推荐\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|bianjituijianqu_3\">\n")));
								String editorRecommend = editorRecommends.substring(editorRecommends.indexOf("<div class=\"book-detail-content\">")).replace("<div class=\"book-detail-content\">", "").trim().replaceAll(filter, "").replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "");
								if (editorRecommend != null && editorRecommend.trim().length() > 0) {
									json.put("editorRecommend", editorRecommend);
								}
							}
							// 获取内容简介
							if (content.contains("<div id=\"detail-tag-id-3\" name=\"detail-tag-id-3\" text=\"内容简介\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|neirongjianjiequ_3\">\n")) {
								String descriptions = content.substring(content.indexOf("<div id=\"detail-tag-id-3\" name=\"detail-tag-id-3\" text=\"内容简介\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|neirongjianjiequ_3\">\n"), content.indexOf("</div>\n        </div>\n    </div>\n", content.indexOf("<div id=\"detail-tag-id-3\" name=\"detail-tag-id-3\" text=\"内容简介\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|neirongjianjiequ_3\">\n")));
								String description = descriptions.substring(descriptions.indexOf("<div class=\"book-detail-content\">")).replace("<div class=\"book-detail-content\">", "").trim().replaceAll(filter, "").replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "");
								if (description != null && description.trim().length() > 0) {
									json.put("description", description);
								}
							}
							// 获取书评
							if (content.contains("<div id=\"detail-tag-id-5\" name=\"detail-tag-id-5\" text=\"精彩书评\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|jingcaishupingqu_3\">\n")) {
								String bookReviews = content.substring(content.indexOf("<div id=\"detail-tag-id-5\" name=\"detail-tag-id-5\" text=\"精彩书评\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|jingcaishupingqu_3\">\n"), content.indexOf("</div>\n        </div>\n    </div>\n", content.indexOf("<div id=\"detail-tag-id-5\" name=\"detail-tag-id-5\" text=\"精彩书评\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|jingcaishupingqu_3\">\n")));
								String bookReview = bookReviews.substring(bookReviews.indexOf("<div class=\"book-detail-content\">")).replace("<div class=\"book-detail-content\">", "").trim().replaceAll(filter, "").replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "");
								if (bookReview != null && bookReview.trim().length() > 0) {
									json.put("bookReview", bookReview);
								}
							}
							// 获取目录
							if (content.contains("<div id=\"detail-tag-id-6\" name=\"detail-tag-id-6\" text=\"目录\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|muluqu_3\">\n")) {
								String indexs = content.substring(content.indexOf("<div id=\"detail-tag-id-6\" name=\"detail-tag-id-6\" text=\"目录\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|muluqu_3\">\n"), content.indexOf("</div>\n        </div>\n", content.indexOf("<div id=\"detail-tag-id-6\" name=\"detail-tag-id-6\" text=\"目录\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|muluqu_3\">\n")));
								if (indexs != null && indexs.trim().length() > 0) {
									String index = indexs.substring(indexs.indexOf("<div class=\"book-detail-content\">")).replace("<div class=\"book-detail-content\">", "").trim().replaceAll(filter, "").replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "");
									json.put("index", index);
								}
							}
							// 获取书摘
							if (content.contains("<div id=\"detail-tag-id-7\" name=\"detail-tag-id-7\" text=\"精彩书摘\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|jingcaishuzhaiqu_3\">\n")) {
								String summarys = content.substring(content.indexOf("<div id=\"detail-tag-id-7\" name=\"detail-tag-id-7\" text=\"精彩书摘\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|jingcaishuzhaiqu_3\">\n"), content.indexOf("</div>\n        </div>\n", content.indexOf("<div id=\"detail-tag-id-7\" name=\"detail-tag-id-7\" text=\"精彩书摘\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|jingcaishuzhaiqu_3\">\n")));
								if (summarys != null && summarys.trim().length() > 0) {
									String summary = summarys.substring(summarys.indexOf("<div class=\"book-detail-content\">")).replace("<div class=\"book-detail-content\">", "").trim().replaceAll(filter, "").replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "");
									json.put("summary", summary);
								}
							}
							// 获取前言
							if (content.contains("<div id=\"detail-tag-id-8\" name=\"detail-tag-id-8\" text=\"前言/序言\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|qianyanqu_3\">\n")) {
								String forewords = content.substring(content.indexOf("<div id=\"detail-tag-id-8\" name=\"detail-tag-id-8\" text=\"前言/序言\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|qianyanqu_3\">\n "), content.indexOf("</div>\n        </div>\n", content.indexOf("<div id=\"detail-tag-id-8\" name=\"detail-tag-id-8\" text=\"前言/序言\" class=\"book-detail-item\" clstag=\"shangpin|keycount|product|qianyanqu_3\">\n ")));
								if (forewords != null && forewords.trim().length() > 0) {
									String foreword = forewords.substring(forewords.indexOf("<div class=\"book-detail-content\">")).replace("<div class=\"book-detail-content\">", "").trim().replaceAll("<" + img + "[^>]*/>", "").replaceAll(filter, "").replaceAll("<" + a + "[^>]+>[^<]*</" + a + ">", "").replaceAll("<" + img + "[^>]* />", "");
									if (!foreword.equals("<p></p>") && foreword != null) {
										json.put("foreword", foreword);
									}
								}
							}
						}
					}
				}
				ja.add(json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (sou.equals("tmall")) {
			try {
				if (url != null && url.trim().length() > 0) {
					doc = Jsoup.connect(url).timeout(10000).header("User-Agent", header).get();
					// 获取基本信息
					// 获取商品名称
					eles = doc.select(div + "[class=tb-detail-hd] " + h);
					if (eles != null) {
						String productName = eles.text().trim();
						if (productName != null && productName.trim().length() > 0) {
							json.put("productName", productName);
						}
					}
					eles = doc.select(ul + "[id=J_AttrUL] " + li);
					// 获取商品原价
					eles1 = eles.select(li + ":contains(定价:)");
					if (eles1 != null && !eles1.isEmpty()) {
						String[] prices = eles1.text().trim().split(":");
						if (prices != null && prices.length > 1) {
							String price = prices[1];
							json.put("price", price);
						}
					}
					// 获取isbn
					eles1 = eles.select(li + ":contains(ISBN编号:)");
					if (eles1 != null && !eles1.isEmpty()) {
						String[] isbns = eles1.text().split(": ");
						if (isbns != null && isbns.length > 1) {
							String barCode = isbns[1];
							json.put("barCode", barCode);
							json.put("isbn", barCode);
						}
					}
					// 获取作者
					eles1 = eles.select(li + ":contains(作者:)");
					if (eles1 != null && !eles1.isEmpty()) {
						String[] authorNamess = eles1.text().split(": ");
						if (authorNamess.length > 1) {
							String authorNames = authorNamess[1];
							json.put("authorNames", authorNames);
						}
					}
					// 获取厂商
					eles1 = eles.select(li + ":contains(出版社名称:)");
					if (eles1 != null && !eles1.isEmpty()) {
						String[] manufacturerNames = eles1.text().split(": ");
						if (manufacturerNames.length > 1) {
							String manufacturerName = manufacturerNames[1];
							json.put("manufacturerName", manufacturerName);
						}
					}
					// 获取出版时间
					eles1 = eles.select(li + ":contains(出版时间:)");
					if (eles1 != null && !eles1.isEmpty()) {
						String[] publishOns = eles1.text().split(": ");
						if (publishOns.length > 1) {
							String publishOn = publishOns[1];
							json.put("publishOn", publishOn);
						}
					}
					// 获取扩展属性
					// 获取开本
					eles1 = eles.select(li + ":contains(开本:)");
					if (eles1 != null && !eles1.isEmpty()) {
						String[] bookSizes = eles1.text().split(": ");
						if (bookSizes.length > 1) {
							String bookSize = bookSizes[1];
							json.put("bookSize", bookSize);
						}
					}
					// 抓取图片
					eles = doc.select(img + "[id=J_ImgBooth]");
					if (eles != null && !eles.isEmpty()) {
						String src1 = eles.attr("src");
						String pictureType = src1.substring(src1.lastIndexOf("."), src1.length());
						String src = src1.substring(0, src1.indexOf(pictureType, 0)) + pictureType;
						if (src != null && !src.startsWith("https:")) {
							src = "https:" + src;
						}
						json.put("src", src);
					}
					// 获取描述信息
					/*
					 * String strDoc = doc.toString(); String[] descUrls =
					 * strDoc.substring(strDoc.indexOf("httpsDescUrl"),
					 * strDoc.indexOf("}",
					 * strDoc.indexOf("httpsDescUrl"))).split(":"); if (descUrls
					 * != null && descUrls.length > 1) { String descUrl =
					 * descUrls[1].replaceAll("[\"]*", ""); if (descUrl != null
					 * && !descUrl.startsWith("https")) { descUrl = "https:" +
					 * descUrl; } Document document =
					 * Jsoup.connect(descUrl).timeout
					 * (10000).header("User-Agent", header).get(); // 获取编辑推荐
					 * eles = document.select(table + ":has(" + td +
					 * ":contains(主编推荐))"); if (eles != null && !eles.isEmpty())
					 * { eles = eles.select(td); if (eles.size() > 1) { String
					 * editorRecommend = eles.get(1).text().trim(); if
					 * (editorRecommend != null &&
					 * editorRecommend.trim().length() > 0) {
					 * json.put("editorRecommend", editorRecommend); } } } //
					 * 获取书评 eles = document.select(table + ":has(" + td +
					 * ":contains(媒体评论))"); if (eles != null && !eles.isEmpty())
					 * { eles = eles.select(td); if (eles.size() > 1) { String
					 * bookReview = eles.get(1).text().trim(); if (bookReview !=
					 * null && bookReview.trim().length() > 0) {
					 * json.put("bookReview", bookReview); } } } // 获取目录 eles =
					 * document.select(table + ":has(" + td + ":contains(目录))");
					 * if (eles != null && !eles.isEmpty()) { eles =
					 * eles.select(td); if (eles.size() > 1) { String index =
					 * eles.get(1).text().trim(); if (index != null &&
					 * index.trim().length() > 0) { json.put("index", index); }
					 * } } // 获取内容简介 eles = document.select(table + ":has(" + td
					 * + ":contains(内容简介))"); if (eles != null &&
					 * !eles.isEmpty()) { eles = eles.select(tr + td); if
					 * (eles.size() > 1) { String description =
					 * eles.get(1).text().trim(); if (description != null &&
					 * description.trim().length() > 0) {
					 * json.put("description", description); } } } }
					 */
				}
				ja.add(json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ja;
	}

	/**
	 * 通过url获取图片
	 */
	public static JSONArray getPictureByWeb(Integer source, String isbn) {
		JSONArray ja = new JSONArray();
		Document doc = null;
		Elements eles = null;
		String href = null;
		if (source == 1) {
			String url = "http://search.dangdang.com/?key=" + isbn;
			try {
				doc = Jsoup.connect(url).timeout(10000).get();
				eles = doc.select(p + "[class=name] " + a);
				if (eles != null) {
					for (Element element : eles) {
						JSONObject json = new JSONObject();
						href = element.attr("href");
						if (href != null) {
							doc = Jsoup.connect(href).timeout(10000).header("User-Agent", header).get();
							// 获取书名
							eles = doc.select(div + "[class=name_info] " + h);
							if (eles != null && !eles.isEmpty()) {
								if (eles.text() != null) {
									String productName = eles.text().trim();
									if (productName != null) {
										json.put("productName", productName);
									}
								}
							} else {
								eles = doc.select(div + "[class=head] " + h);
								if (eles.text() != null) {
									String productName = eles.text().trim();
									if (productName != null) {
										json.put("productName", productName);
									}
								}
							}
							// 获取图片
							eles = doc.select(div + "[class=pic] " + img + "[id=largePic]");
							if (eles != null && !eles.isEmpty()) {
								String src = eles.attr("src");
								if (src != null && src.length() > 0 && src.startsWith("http://")) {
									src = src.replaceAll("_w", "_o");
									json.put("src", src);
								} else {
									String src1 = eles.attr("wsrc");
									if (src1 != null && src1.length() > 0 && src1.startsWith("http://")) {
										src1 = src1.replaceAll("_w", "_o");
										json.put("src", src1);
									}
								}
							}
						}
						ja.add(json);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (source == 2) {
			String url = "http://search.jd.com/Search?keyword=" + isbn;
			try {
				doc = Jsoup.connect(url).timeout(10000).get();
				eles = doc.select(div + "[class=p-name] " + a);
				if (eles != null) {
					for (Element element : eles) {
						JSONObject json = new JSONObject();
						href = element.attr("href");
						if (href != null && !href.startsWith("http:")) {
							href = "http:" + href;
						}
						doc = Jsoup.connect(href).timeout(10000).header("User-Agent", header).get();
						// 获取商品名称
						eles = doc.select(div + "[id=name] " + h);
						if (eles != null && !eles.isEmpty()) {
							if (eles.text() != null) {
								String productName = eles.text().trim();
								if (productName != null) {
									json.put("productName", productName);
								}
							}
						} else {
							eles = doc.select(div + "[id=name] " + h2);
							if (eles != null && !eles.isEmpty()) {
								if (eles.text() != null) {
									String productName = eles.text().trim();
									if (productName != null) {
										json.put("productName", productName);
									}
								}
							}
						}
						eles = doc.select(div + "[class=jqzoom] " + img);
						if (eles != null && !eles.isEmpty()) {
							String src = eles.attr("src").replace("/n0", "/imgzone").replace("/n1", "/imgzone");
							if (src != null && !src.startsWith("http:")) {
								src = "http:" + src;
							}
							json.put("src", src);
						}
						ja.add(json);
					}
				}
			} catch (Exception e) {

			}
		} else if (source == 3) {
			String url = "https://list.tmall.com/search_product.htm?q=" + isbn;
			try {
				doc = Jsoup.connect(url).timeout(10000).get();
				eles = doc.select(p + "[class=productTitle] " + a);
				for (Element element : eles) {
					JSONObject json = new JSONObject();
					String src1 = element.attr("href");
					if (src1 != null && src1.trim().length() > 0 && !src1.startsWith("https")) {
						href = "https:" + src1;
					}
					if (href != null && href.trim().length() > 0) {
						doc = Jsoup.connect(href).timeout(10000).header("User-Agent", header).get();
						// 获取基本信息
						// 获取商品名称
						eles = doc.select(div + "[class=tb-detail-hd] " + h);
						if (eles != null) {
							String productName = eles.text().trim();
							if (productName != null && productName.trim().length() > 0) {
								json.put("productName", productName);
							}
						}
					}
					// 抓取图片
					eles = doc.select(img + "[id=J_ImgBooth]");
					if (eles != null && !eles.isEmpty()) {
						src1 = eles.attr("src");
						String pictureType = src1.substring(src1.lastIndexOf("."), src1.length());
						href = src1.substring(0, src1.indexOf(pictureType, 0)) + pictureType;
						if (href != null && !href.startsWith("https:")) {
							href = "https:" + href;
						}
						json.put("src", href);
					}
					ja.add(json);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ja;
	}

	public static JSONObject getDdDataByUrl(String url) {
		String wId = url.substring(url.indexOf("com/") + 4, url.lastIndexOf(".html")).trim();
		Long goodsId = Long.parseLong(wId);
		String url1 = "http://detail.dangdang.com/?r=callback%2Fdetail&productId=" + goodsId + "&templateType=publish&describeMap=&shopId=0&categoryPath=01.03.33.01.00.00";
		Map<String, String> harss = new HashMap<String, String>();
		harss.put("X-Requested-With", "XMLHttpRequest");
		String results = HttpClientUtil.getClientInvoke(url1, harss, "gbk");
		String detailMes = "";
		if (results != null && results.trim().length() > 0) {
			JSONObject detail = JSONObject.parseObject(results);
			if (detail != null) {
				String detail1 = detail.getString("data");
				if (detail1 != null && detail1.trim().length() > 0) {
					detailMes = detail1;
					JSONObject jsona = JSONObject.parseObject(detailMes);
					return jsona;
				}
			}
		}
		return null;
	}
}
