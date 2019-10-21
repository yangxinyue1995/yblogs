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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 抓取数据使用，新版本
 * 
 * @author zj
 * @version
 * @since JDK 1.7
 */
public class GetSourceFromWebUtil_V1 {

	private static final String div = "div";
	private static final String h = "h1";
	private static final String li = "li";
	private static final String ul = "ul";
	private static final String span = "span";
	private static final String img = "img";
	public static final String header = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.22 Safari/537.36";

	/**
	 * 
	 * @author FW
	 * @param filter
	 * @param sou
	 * @param isbn
	 * @return 通过isbn获取数据
	 * @since JDK 1.7
	 */
	public static JSONArray getSourceFromWebByIsbn(String filter, String sou,
			String isbn) {
		String url = null;
		Document doc = null;
		JSONArray ja = new JSONArray();

		try {
			// 当当
			if (sou.equals("1")) {
				url = "http://search.dangdang.com/?key=" + isbn
						+ "&filter=0%7C0%7C0%7C0%7C0%7C1%7C0%7C0%7C0%7C0%7C";
				doc = Jsoup.connect(url).timeout(10000).get();
				Elements eles = doc.select("p[class=name] a");
				for (Element element : eles) {
					JSONObject json = new JSONObject();
					String href = element.attr("href");
					doc = Jsoup.connect(href).timeout(10000)
							.header("User-Agent", header).get();

					// 获取商品描述信息
					String wId = href.substring(href.indexOf("com/") + 4,
							href.lastIndexOf(".html")).trim();
					Long goodsId = Long.parseLong(wId);
					json = analysisDocumentByDangdang(json, doc, filter,
							goodsId);

					ja.add(json);
				}
			} else if (sou.equals("2")) { // 从京东自营获取数据
				url = "http://search.jd.com/Search?keyword=" + isbn
						+ "&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wtype=1&click=1";
				doc = Jsoup.connect(url).timeout(10000).get();
				
				Element ul = null;
				try {
					if (doc.select(".goods-list-v1 ul").size() > 0) {
						ul = doc.select(".goods-list-v1 ul").get(0);
					} else if (doc.select("[class=goods-list-v2 gl-type-4 J-goods-list] ul").size() > 0) {
						ul = doc.select("[class=goods-list-v2 gl-type-4 J-goods-list] ul").get(0);
					}else if (doc.select("[class=goods-list-v2 gl-type-1 J-goods-list] ul").size() > 0) {
						ul = doc.select("[class=goods-list-v2 gl-type-1 J-goods-list] ul").get(0);
					}
				} catch (IndexOutOfBoundsException e) {
					return null;
				}
				Elements lis = ul.children();
				
				for (Element element : lis) {
					
					Element a = element.select("a").get(0);
					
					Element pImg = element.select(".p-img").get(0);
					Element divDataCat = pImg.select("div[data-catid]").get(0);
					String eBook = divDataCat.attr("class");
					
					if(eBook!=null && eBook.equals("picon pi-ebook")){
						// 电子书
						continue;
					}
					
					JSONObject json = new JSONObject();
					String href = a.attr("href");
					String srcs = href.replaceAll("//", "");
					if (srcs.startsWith("e.jd.com")) {
						// 电子书
						continue;
					}
					if (href.indexOf("http:") == -1) {
						href = "http:" + href;
					}
					// 跳转到商品详情页面
					doc = Jsoup.connect(href).timeout(10000)
							.header("User-Agent", header).get();
					// 获取商品基本信息
					json = analysisDocumentByJd(json, doc, filter);

					ja.add(json);
				}
			} else if (sou.equals("3")) { // 从天猫获取数据
				url = "https://list.tmall.com/search_product.htm?q=" + isbn;
				String src = null;

				doc = Jsoup.connect(url).timeout(10000).get();
				Elements eles = doc.select("p[class=productTitle] a");
				for (Element element : eles) {
					JSONObject json = new JSONObject();
					String src1 = element.attr("href");
					if (src1 != null && src1.trim().length() > 0
							&& !src1.startsWith("https")) {
						src = "https:" + src1;
					}
					if (src != null && src.trim().length() > 0) {
						doc = Jsoup.connect(src).timeout(10000)
								.header("User-Agent", header).get();
						json = analysisDocumentByTmall(json, doc, filter, false);
					}
					ja.add(json);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ja;
	}

	/**
	 * 通过url获取数据
	 */
	public static JSONArray getSourceFromWebByUrl(String filter, String sou,
			String url) {
		Document doc = null;
		JSONObject json = new JSONObject();
		JSONArray ja = new JSONArray();

		if (url == null || url.length() == 0) {
			return null;
		}

		try {
			if (sou.equals("dangdang")) {
				doc = Jsoup.connect(url).timeout(10000)
						.header("User-Agent", header).get();

				// 获取商品描述信息
				String wId = url.substring(url.indexOf("com/") + 4,
						url.lastIndexOf(".html")).trim();
				Long goodsId = Long.parseLong(wId);

				json = analysisDocumentByDangdang(json, doc, filter, goodsId);

			} else if (sou.equals("jd")) {

				String wId = url.substring(url.indexOf("com/") + 4,
						url.lastIndexOf(".html")).trim();
				Long goodsId = Long.parseLong(wId);
				String url1 = "https://p.3.cn/prices/get?type=1&area=1_72_2799&pdtk=&pduid=587231331&pdbp=0&skuid=J_"
						+ goodsId + "&callback=cnp";
				Map<String, String> harss = new HashMap<String, String>();
				harss.put("X-Requested-With", "XMLHttpRequest");
				String results = HttpClientUtil.getClientInvoke(url1, harss,
						"gbk");
				if (results != null && results.length() > 0) {
					results = results.substring(results.indexOf("cnp(") + 4,
							results.indexOf(")"));
					JSONArray pArray = JSONArray.parseArray(results);
					JSONObject pJson = (JSONObject) pArray.get(0);
					String price = pJson.getString("m");
					json.put("price", price);
				}

				doc = Jsoup.connect(url).timeout(10000)
						.header("User-Agent", header).get();

				json = analysisDocumentByJd(json, doc, filter);

			} else if (sou.equals("tmall")) {
				doc = Jsoup.connect(url).timeout(10000)
						.header("User-Agent", header).get();
				json = analysisDocumentByTmall(json, doc, filter, false);
			}

			ja.add(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ja;
	}

	/**
	 * 解析当当文档
	 * 
	 * @return
	 */
	public static JSONObject analysisDocumentByDangdang(JSONObject json,
			Document doc, String filter, Long goodsId) {

		Elements eles = null;
		Elements eles1 = null;

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
		eles = doc.select(div + "[class=price_m]");
		if (eles != null && !eles.isEmpty()) {
			if (eles.text() != null) {
				String price = eles.text().split(":")[0].trim().replaceAll("¥",
						"");
				if (price != null && price.length() > 0) {
					json.put("price", price);
				}
			}
		} else {
			eles = doc.select(span + "[id=originalPriceTag]");
			if (eles.text() != null) {
				String price = eles.text().split(":")[0].trim().replaceAll("¥",
						"");
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
			eles = doc.select(div + "[class=clearfix m_t6]:has(" + div
					+ ":contains(ＩＳＢＮ)) " + div
					+ "[class=show_info_right max_width]");
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
					if (manufacturerName != null
							&& manufacturerName.length() > 0) {
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
			eles = doc.select(div + "[class=clearfix m_t6]:has(" + div
					+ ":contains(作)) " + div
					+ "[class=show_info_right max_width] a");
			if (eles != null && !eles.isEmpty()) {
				String authorNames = eles.text().trim().split(":")[0];
				if (authorNames != null && authorNames.length() > 0) {
					json.put("authorNames", authorNames);
				}
			}
			// 获取厂商名称
			eles = doc.select(div + "[class=clearfix m_t6]:has(" + div
					+ ":contains(出)) " + div
					+ "[class=show_info_right max_width] a");
			if (eles != null && !eles.isEmpty()) {
				String manufacturerName = eles.text().trim();
				if (manufacturerName != null && manufacturerName.length() > 0) {
					json.put("manufacturerName", manufacturerName);
				}
			}
			// 获取的出版时间
			eles = doc.select(div + "[class=clearfix m_t6]:has(" + div
					+ ":contains(出版时间)) " + div
					+ "[class=show_info_right max_width]");
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
				String wordNum = eles1.text().substring(
						eles1.text().lastIndexOf("：") + 1);
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
			eles = doc.select(div + "[ class=pro_content] " + ul
					+ "[class=key clearfix] " + li);
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
					String wordNum = eles1.text().substring(
							eles1.text().lastIndexOf("：") + 1);
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
		json = analysisDetailsDangdang(json, goodsId, filter);

		// 获取图片
		eles = doc.select(div + "[class=pic] " + img + "[id=largePic]");
		if (eles != null && !eles.isEmpty()) {
			String src = eles.attr("src");
			if (src != null && src.length() > 0) {
				src = src.replaceAll("_w", "_o").replaceAll("_u", "_o");
				json.put("src", src);
			}
		}

		return json;
	}

	/**
	 * 解析京东文档
	 * 
	 * @return
	 */
	public static JSONObject analysisDocumentByJd(JSONObject json,
			Document doc, String filter) {

		Elements eles = null;
		Elements eles1 = null;

		eles = doc.select(div + "[class=sku-name]");
		if (eles != null && !eles.isEmpty()) {
			if (eles.text() != null) {
				String productName = eles.text().trim();
				json.put("productName", productName);
			}
		}
		// 获取ISBN
		eles = doc.select(div + "[class=p-parameter] " + li);
		if (eles != null && !eles.isEmpty()) {
			eles1 = eles.select(li + ":contains(ISBN：)");
			if (eles1 != null && !eles1.isEmpty()) {
				String barCode = eles1.text().replaceAll("[^0-9]", "");
				json.put("barCode", barCode);
				json.put("isbn", barCode);
			}
			// 获取厂商名称
			eles1 = eles.select(li + ":contains(出版社： )");
			if (eles1 != null && !eles1.isEmpty()) {
				String manufacturerName = eles1.text().trim().split("： ")[1];
				json.put("manufacturerName", manufacturerName);
			}
			// 获取出版时间
			eles1 = eles.select(li + ":contains(出版时间：)");
			if (eles1 != null && !eles1.isEmpty()) {
				String publishOn = eles1.text().trim().split("：")[1];
				json.put("publishOn", publishOn);
			}
			// 获取版次
			eles1 = eles.select(li + ":contains(版次：)");
			if (eles1 != null && !eles1.isEmpty()) {
				String edition = eles1.text().trim().split("：")[1];
				json.put("edition", edition);
			}
			// 获取包装
			eles1 = eles.select(li + ":contains(包装：)");
			if (eles1 != null && !eles1.isEmpty()) {
				String cover = eles1.text().trim().split("：")[1];
				json.put("cover", cover);
			}
			// 获取开本
			eles1 = eles.select(li + ":contains(开本：)");
			if (eles1 != null && !eles1.isEmpty()) {
				String bookSize = eles1.text().trim().split("：")[1];
				json.put("bookSize", bookSize);
			}
			// 获取页数
			eles1 = eles.select(li + ":contains(页数：)");
			if (eles1 != null && !eles1.isEmpty()) {
				String pages = eles1.text().trim().split("：")[1];
				json.put("pages", pages);
			}
			// 获取字数
			eles1 = eles.select(li + ":contains(字数：)");
			if (eles1 != null && !eles1.isEmpty()) {
				String wordNum = eles1.text().trim().split("：")[1];
				json.put("wordNum", wordNum);
			}
		}
		// 获取 作者
		eles = doc.select(div + "[class=p-author] a");
		// 获取作者
		if (eles != null && !eles.isEmpty()) {
			String authorNames = eles.text().trim().split(" ")[0];
			json.put("authorNames", authorNames);
		}
		// 获取图片
		eles = doc.select(div + "[id=spec-n1] " + img);
		if (eles != null && !eles.isEmpty()) {
			String src = eles.attr("src").replace("/n0", "/imgzone")
					.replace("/n1", "/imgzone");
			if (src != null && !src.startsWith("http:")) {
				src = "http:" + src;
			}
			json.put("src", src);
		}

//		// 获取 定价
//		Element priceEle = doc.select("[rel=canonical]").get(0);
//		String priceHref = priceEle.attr("href");
//		// 获取定价
//		String skuId = priceHref.substring(priceHref.indexOf("com/") + 4,
//				priceHref.indexOf(".html"));
//
//		String priceUrl = "http://p.3.cn/prices/get?skuid=J_" + skuId;
//
//		String priceResult = HttpClientUtil.getClientInvoke(priceUrl, null,
//				"gbk");
//		JSONArray priceJa = JSONArray.parseArray(priceResult);
//		JSONObject priceJo = priceJa.getJSONObject(0);
//
//		String price = priceJo.getString("m");
//		json.put("price", price);

		// 描述信息
		String scri = doc.toString();
		String wareIds = scri.substring(scri.indexOf("qualityLife:"),
				scri.indexOf("colorSize:"));
		String wareId = wareIds.substring(wareIds.indexOf("pid="),
				wareIds.indexOf("&catId")).replaceAll("[^0-9]", "");
		String inforUrl = "http://d.3.cn/desc/" + wareId
				+ "?cdn=2&callback=showdesc";
		Map<String, String> hars = new HashMap<String, String>();
		hars.put("X-Requested-With", "XMLHttpRequest");
		String result = HttpClientUtil.getClientInvoke(inforUrl, hars, "gbk");
		if (result != null) {
			String te = result.replaceAll("[showdesc]+" + "[(]+", " ")
					.replaceAll("[)]+", " ");
			JSONObject jsons = JSONObject.parseObject(te);
			String content = jsons.getString("content");

			// 替换图片等标签
			content = StringEscapeUtils.unescapeHtml(content);
			String regEx_a = "<[\\s]*?a[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?a[\\s]*?>";
			String regEx_img = "<[\\s]*?img[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?img[\\s]*?>";

			Pattern p = Pattern.compile(regEx_a, Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(content);
			content = m.replaceAll(""); // 过滤a标签

			p = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
			m = p.matcher(content);
			content = m.replaceAll(""); // 过滤img标签

			content = content.replaceAll(filter, "");

			// 转为文档，用选择器来获取内容
			Document docDetals = Jsoup.parse(content);
			doc.select("img").remove();// 删除img标签
			doc.select("a").remove();// 删除A标签

			String editorRecommend = "";
			String description = "";
			String authorDesc = "";
			String index = "";
			String foreword = "";
			String bookReview = "";
			String summary = "";

			// 编辑推荐
			Elements eEles = docDetals
					.select("div[text=编辑推荐] div[class=book-detail-content]");
			editorRecommend = eEles.html();

			// 获取简介
			eEles = docDetals
					.select("div[text=内容简介] div[class=book-detail-content]");
			description = eEles.html();

			// 获取作者简介
			eEles = docDetals
					.select("div[text=作者简介] div[class=book-detail-content]");
			authorDesc = eEles.html();

			// 获取目录
			eEles = docDetals
					.select("div[text=目录] div[class=book-detail-content]");
			index = eEles.html();

			// 获取前言
			eEles = docDetals
					.select("div[text=前言/序言] div[class=book-detail-content]");
			foreword = eEles.html();

			// 获取书评
			eEles = docDetals
					.select("div[text=精彩书评] div[class=book-detail-content]");
			bookReview = eEles.html();

			// 在线试读
			eEles = docDetals
					.select("div[text=精彩书摘] div[class=book-detail-content]");
			summary = eEles.html();

			json.put("editorRecommend", editorRecommend);
			json.put("description", description);
			json.put("authorDesc", authorDesc);
			json.put("index", index);
			json.put("foreword", foreword);
			json.put("bookReview", bookReview);
			json.put("summary", summary);
		}

		return json;
	}

	private static String remove(String resource, char ch) {
		StringBuffer buffer = new StringBuffer();
		int position = 0;
		char currentChar;

		while (position < resource.length()) {
			currentChar = resource.charAt(position++);
			if (currentChar != ch)
				buffer.append(currentChar);
		}
		return buffer.toString();
	}

	/**
	 * 解析天猫文档
	 * 
	 * @return
	 */
	public static JSONObject analysisDocumentByTmall(JSONObject json,
			Document doc, String filter, boolean isDelImg) {
		
		LogUtil.error("复制外部商品！", "进入淘宝数据，分析方法！！");

		Elements eles = null;
		Elements eles1 = null;

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
				if (price != null) {
					price = price.replace("元", "").trim();
					price = remove(price, ' ');
					Pattern p = Pattern.compile("\\s*|\t|\r|\n");
					Matcher m = p.matcher(price);
					price = m.replaceAll("");

					json.put("price", price);
				}
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
			String pictureType = src1.substring(src1.lastIndexOf("."),
					src1.length());
			String src = src1.substring(0, src1.indexOf(pictureType, 0))
					+ pictureType;
			if (src != null && !src.startsWith("https:")) {
				src = "https:" + src;
			}
			json.put("src", src);
		}

		// 详情分析
		analysisTmall(doc.toString(), json, filter, isDelImg);

		return json;
	}

	/**
	 * 天猫描述信息。扩展信息获取
	 * 
	 * @param doc
	 *            jsoup抓取的 document 字符串
	 * @param json
	 *            需要返回的json
	 * @param filter
	 *            关键词过滤
	 * @param isDelImg
	 *            是否保留详情图片
	 * @return
	 */
	public static JSONObject analysisTmall(String doc, JSONObject json,
			String filter, boolean isDelImg) {
		
		LogUtil.error("复制外部商品！", "进入淘宝数据，分析详情方法！！");
		
		// 把页面转换为string
		String strDoc = doc;

		// 商品ID
		String itemId = strDoc.substring(strDoc.indexOf("itemId:\""),
				strDoc.indexOf(",", strDoc.indexOf("itemId:\"")) - 1);
		itemId = itemId.substring(itemId.indexOf("\"") + 1, itemId.length());
		json.put("itemId", itemId);
		// 淘宝产品Id
//		String pidStr = strDoc.substring(
//				strDoc.indexOf("showBreadCrumb=false&spuId="),
//				strDoc.indexOf("&showSpuMaintainer"));
		String pidStr = strDoc.substring(strDoc.indexOf("\"spuId\":\""),strDoc.indexOf(",\"categoryId\""));
		pidStr = pidStr
				.substring(9, pidStr.length()-1);
		json.put("taobaoPid", pidStr);
		// 淘宝分类ID
		String cidStr = strDoc.substring(strDoc.indexOf("categoryId:"),
				strDoc.indexOf(",notInitSearchBar"));
		cidStr = cidStr.substring(cidStr.indexOf(":") + 1, cidStr.length());
		json.put("taobaoCid", cidStr);
		// 获取描述信息
		// 获取描述详情的url
		String descUrl = "";
		String[] descUrls = strDoc.substring(strDoc.indexOf("httpsDescUrl"),
				strDoc.indexOf("}", strDoc.indexOf("httpsDescUrl"))).split(":");
		descUrl = descUrls[1].replaceAll("[\"]*", "");
		if (descUrl != null && !descUrl.startsWith("https")) {
			descUrl = "http:" + descUrl;
		}

		LogUtil.error("复制外部商品！", "进入淘宝数据，获取描述详情URL：--" + descUrl);
		Map<String, String> harss = new HashMap<String, String>();
		harss.put("X-Requested-With", "XMLHttpRequest");
		String results = HttpClientUtil.getClientInvoke(descUrl, harss, "gbk");
		LogUtil.error("复制外部商品！", "进入淘宝数据，获取描述详情URL--执行结束：--" + results);
		if (results != null && results.length() > 0) {
			results = results.substring(results.indexOf("desc='") + 6,
					results.lastIndexOf("';"));
		}

		String regEx_a = "<[\\s]*?a[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?a[\\s]*?>";
		Pattern p = Pattern.compile(regEx_a, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(results);
		results = m.replaceAll(""); // 过滤a标签

		// 获取详情是否是模块。化的，并且找到对应关系
		String descAnchors = strDoc.substring(strDoc.indexOf("descAnchors"),
				strDoc.indexOf("TShop.Setup"));
		descAnchors = descAnchors.substring(descAnchors.indexOf(":") + 1,
				descAnchors.lastIndexOf("]") + 1);
		JSONArray descAnja = JSONArray.parseArray(descAnchors);
		if (descAnja != null && descAnja.size() > 0) {
			// 模块化
			for (Object object : descAnja) {
				JSONObject jo = (JSONObject) object;
				// 商品参数
				// 细节图
				// 内容介绍
				// 作者介绍
				// 目录
				// 媒体评论
				// 在线试读

				// 获取前言 没有内容

				String text = analysisDetailsTmall(jo, results, filter,
						isDelImg);
				if (jo.getString("moduleName").equals("商品参数")) {
					json.put("itemDetails", text);
				} else if (jo.getString("moduleName").equals("细节图")) {
					json.put("itemImgs", text);
				} else if (jo.getString("moduleName").equals("内容介绍")) {
					json.put("description", text);
				} else if (jo.getString("moduleName").equals("作者介绍")) {
					json.put("authorDesc", text);
				} else if (jo.getString("moduleName").equals("目录")) {
					json.put("index", text);
				} else if (jo.getString("moduleName").equals("媒体评论")) {
					json.put("bookReview", text);
				} else if (jo.getString("moduleName").equals("在线试读")) {
					json.put("summary", text);
				}
			}

		} else {

			String regEx_img = "<[\\s]*?img[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?img[\\s]*?>";
			p = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
			m = p.matcher(results);
			results = m.replaceAll(""); // 过滤img标签

			results = results.replaceAll(filter, "");

			// 获取内容简介 把所有内容都作为内容简介
			json.put("description", results);
		}

		return json;
	}
	
	/**
	 * 抓取天猫基本信息，产品ID ，分类等
	 * @param json
	 * @param doc
	 * @param filter
	 * @return
	 */
	public static JSONObject analysisDocumentByTmallQuery(String url, String cookies, String filter) {
		Document doc = null;
		JSONObject json = new JSONObject();
		try {
			
			String cookiess[] = cookies.split(";");
			String token = null;
			for (String str : cookiess) {
				String kv[] = str.split("=");
				if (kv[0].equals("_tb_token_")) {
					token = kv[1];
					break;
				}
			}
			
			doc = Jsoup.connect(url+"&_tb_token_="+token).timeout(5000)
					.header("User-Agent", GetSourceFromWebUtil_V1.header)
					.header("referer", "https://list.tmall.com/search_product.htm?q=123&type=p&spm=a220m.1000858.a2227oh.d100&from=.list.pc_1_searchbutton")
					.header("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36")
					.header("cookie", cookies)
					.get();
			
			Elements spansCount = doc.select(".j_ResultsNumber > span");
			Integer cnt = Integer.parseInt(spansCount.get(0).html());
			if(cnt == 0){
				return json;
			}
			
			Elements divProducts = doc.select(".grid-nosku .product");
			// 拿到排名第一的产品
			Element topOnePro = null;
			try {
				topOnePro = divProducts.get(0);
			} catch (Exception e) {
				System.out.println(url);
			}
			// 分析商品链接
			Elements aels = topOnePro.select("a[class=productImg]");
			
			String itemUrl = "https://"+aels.get(0).attr("href").replace("//", "")+"&_tb_token_="+token;
			
			doc = Jsoup.connect(itemUrl).timeout(5000)
					.header("User-Agent", GetSourceFromWebUtil_V1.header)
					.header("referer", "https://list.tmall.com/search_product.htm?q=123&type=p&spm=a220m.1000858.a2227oh.d100&from=.list.pc_1_searchbutton")
					.header("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36")
					.header("cookie", cookies)
					.get();
			
			Elements eles = null;
			Elements eles1 = null;

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
					if (price != null) {
						price = price.replace("元", "").trim();
						price = remove(price, ' ');
						Pattern p = Pattern.compile("\\s*|\t|\r|\n");
						Matcher m = p.matcher(price);
						price = m.replaceAll("");

						json.put("price", price);
					}
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
			
			// 把页面转换为string
			String strDoc = doc.toString();

			// 商品ID
			String itemId = strDoc.substring(strDoc.indexOf("itemId:\""),
					strDoc.indexOf(",", strDoc.indexOf("itemId:\"")) - 1);
			itemId = itemId.substring(itemId.indexOf("\"") + 1, itemId.length());
			json.put("itemId", itemId);

			// 淘宝产品Id
			String pidStr = strDoc.substring(
					strDoc.indexOf("showCompanyPurchase\":false,\"spuId\":\""),
					strDoc.indexOf("\",\"title"));
			pidStr = pidStr
					.substring(pidStr.lastIndexOf("\"") + 1, pidStr.length());
			json.put("taobaoPid", pidStr);
			// 淘宝分类ID
			String cidStr = strDoc.substring(strDoc.indexOf("categoryId:"),
					strDoc.indexOf(",notInitSearchBar"));
			cidStr = cidStr.substring(cidStr.indexOf(":") + 1, cidStr.length());
			json.put("taobaoCid", cidStr);
			
		} catch (Exception e) {
			e.printStackTrace();
			json.put("state", false);
			json.put("infor", "复制错误，抓取程序异常：\n"+e.getMessage());
			return json;
		}
		
		return json;
	}

	/**
	 * 获取天猫模块化描述
	 * 
	 * @param jo
	 * @param results
	 * @return
	 */
	public static String analysisDetailsTmall(JSONObject jo, String results,
			String filter, boolean isDelImg) {
		String id = jo.getString("anchorId");
		String lastIndex = id.substring(id.length() - 1, id.length());
		int lastint = Integer.parseInt(lastIndex) + 1;
		String endId = id.substring(0, id.length() - 1) + lastint;

		String startImg = "<img class=\"desc_anchor\" id=\""
				+ id
				+ "\" src=\"https://assets.alicdn.com/kissy/1.0.0/build/imglazyload/spaceball.gif\" />";
		String endImg = "<img class=\"desc_anchor\" id=\""
				+ endId
				+ "\" src=\"https://assets.alicdn.com/kissy/1.0.0/build/imglazyload/spaceball.gif\" />";
		
		if(results.indexOf(startImg) == -1){
			startImg = "<img class=\"desc_anchor\" id=\""
					+ id
					+ "\" src=\"https://assets.alicdn.com/kissy/1.0.0/build/imglazyload/spaceball.gif\">";
			endImg = "<img class=\"desc_anchor\" id=\""
					+ endId
					+ "\" src=\"https://assets.alicdn.com/kissy/1.0.0/build/imglazyload/spaceball.gif\">";
		}
		
		String text = null;
		if (results.indexOf(endImg) == -1) {
			text = results.substring(results.indexOf(startImg),
					results.length());
		} else {
			text = results.substring(results.indexOf(startImg),
					results.indexOf(endImg));
		}
		text = text.substring(text.indexOf(">") + 1, text.length());
		text = text.replaceAll(filter, "");

		if (!isDelImg) {
			String regEx_img = "<\\s*img\\s+([^>]*)\\s*>";
			Pattern p = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(text);
			text = m.replaceAll(""); // 过滤img标签

			regEx_img = "<[\\s]*?img[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?img[\\s]*?>";
			p = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
			m = p.matcher(text);
			text = m.replaceAll(""); // 过滤img标签
		}

		return text;
	}

	/**
	 * 处理当当详情
	 * 
	 * @param json
	 * @param desc
	 * @return
	 */
	public static JSONObject analysisDetailsDangdang(JSONObject json,
			Long goodsId, String filter) {

		// 获取商品描述信息
		// String url1 =
		// "http://detail.dangdang.com/?r=callback%2Fdetail&productId=" +
		// goodsId +
		// "&templateType=publish&describeMap=&shopId=0&categoryPath=01.03.33.01.00.00";
		String url1 = "http://product.dangdang.com/?r=callback%2Fdetail&productId="
				+ goodsId
				+ "&templateType=publish&describeMap=&shopId=0&categoryPath=01.03.33.01.00.00";
		Map<String, String> harss = new HashMap<String, String>();
		harss.put("X-Requested-With", "XMLHttpRequest");
		String results = HttpClientUtil.getClientInvoke(url1, harss, "gbk");

		if (results != null && results.trim().length() > 0) {
			
			JSONObject detail = JSONObject.parseObject(results);
			detail = detail.getJSONObject("data");
			String detailMes = detail.getString("html");

			if (detailMes == null || detailMes.length() == 0) {
				return json;
			}

			detailMes = StringEscapeUtils.unescapeHtml(detailMes);
			String regEx_a = "<[\\s]*?a[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?a[\\s]*?>";
			String regEx_img = "<[\\s]*?img[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?img[\\s]*?>";
			String regEx_img1 = "<\\s*img\\s+([^>]*)\\s*>";

			Pattern p = Pattern.compile(regEx_a, Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(detailMes);
			detailMes = m.replaceAll(""); // 过滤a标签

			p = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
			m = p.matcher(detailMes);
			detailMes = m.replaceAll(""); // 过滤img标签

			p = Pattern.compile(regEx_img1, Pattern.CASE_INSENSITIVE);
			m = p.matcher(detailMes);
			detailMes = m.replaceAll(""); // 过滤img标签

			// 转为文档，用选择器来获取内容
			Document doc = Jsoup.parse(detailMes);
			doc.select("img").remove();// 删除img标签
			doc.select("a").remove();// 删除A标签
            doc.select("embed").remove();//删除embed标签
            
			String editorRecommend = "";
			String description = "";
			String authorDesc = "";
			String index = "";
			String foreword = "";
			String bookReview = "";
			String summary = "";

			// 编辑推荐
			Elements eEles = doc.select("div[id=abstract] div[class=descrip]");
			Elements justSpanAll = eEles.select("#abstract-all");
			if (justSpanAll != null && justSpanAll.size() > 0) {
				// 说明该字段不需要在textarea 获取所有内容
				editorRecommend = eEles.html();
			} else {
				editorRecommend = eEles.select("textarea").val();
			}

			// 获取简介
			eEles = doc.select("div[id=content] div[class=descrip]");
			justSpanAll = eEles.select("#content-all");
			if (justSpanAll != null && justSpanAll.size() > 0) {
				// 说明该字段不需要在textarea 获取所有内容
				description = eEles.html();
			} else {
				description = eEles.select("textarea").val();
			}

			// 获取作者简介
			eEles = doc.select("div[id=authorIntroduction] div[class=descrip]");
			justSpanAll = eEles.select("#authorIntroduction-all");
			if (justSpanAll != null && justSpanAll.size() > 0) {
				// 说明该字段不需要在textarea 获取所有内容
				authorDesc = eEles.html();
			} else {
				authorDesc = eEles.select("textarea").val();
			}

			// 获取目录
			eEles = doc.select("div[id=catalog] div[class=descrip]");
			justSpanAll = eEles.select("#catalog-all");
			if (justSpanAll != null && justSpanAll.size() > 0) {
				// 说明该字段不需要在textarea 获取所有内容
				index = eEles.html();
			} else {
				index = eEles.select("textarea").val();
			}

			// 获取前言
			eEles = doc.select("div[id=preface] div[class=descrip]");
			justSpanAll = eEles.select("#preface-all");
			if (justSpanAll != null && justSpanAll.size() > 0) {
				// 说明该字段不需要在textarea 获取所有内容
				foreword = eEles.html();
			} else {
				foreword = eEles.select("textarea").val();
			}

			// 获取书评
			eEles = doc.select("div[id=mediaFeedback] div[class=descrip]");
			justSpanAll = eEles.select("#mediaFeedback-all");
			if (justSpanAll != null && justSpanAll.size() > 0) {
				// 说明该字段不需要在textarea 获取所有内容
				bookReview = eEles.html();
			} else {
				bookReview = eEles.select("textarea").val();
			}

			// 在线试读
			eEles = doc.select("div[id=extract] div[class=descrip]");
			justSpanAll = eEles.select("#extract-all");
			if (justSpanAll != null && justSpanAll.size() > 0) {
				// 说明该字段不需要在textarea 获取所有内容
				summary = eEles.html();
			} else {
				summary = eEles.select("textarea").val();
			}

			json.put("editorRecommend", editorRecommend!=null?editorRecommend.replaceAll(filter, ""):"");
			json.put("description", description!=null?description.replaceAll(filter, ""):"");
			json.put("authorDesc",authorDesc!=null? authorDesc.replaceAll(filter, ""):"");
			json.put("index", index!=null? index.replaceAll(filter, ""):"");
			json.put("foreword", foreword!=null ? foreword.replaceAll(filter, "") :"");
			json.put("bookReview", bookReview!=null ? bookReview.replaceAll(filter, "") :"");
			json.put("summary", summary != null ? summary.replaceAll(filter, "") : "");

		}

		return json;
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
				eles = doc.select("p[class=name] a");
				if (eles != null) {
					for (Element element : eles) {
						JSONObject json = new JSONObject();
						href = element.attr("href");
						if (href != null) {
							doc = Jsoup.connect(href).timeout(10000)
									.header("User-Agent", header).get();
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
							eles = doc.select(div + "[class=pic] " + img
									+ "[id=largePic]");
							if (eles != null && !eles.isEmpty()) {
								String src = eles.attr("src");
								if (src != null && src.length() > 0
										&& src.startsWith("http://")) {
									src = src.replaceAll("_w", "_o");
									json.put("src", src);
								} else {
									String src1 = eles.attr("wsrc");
									if (src1 != null && src1.length() > 0
											&& src1.startsWith("http://")) {
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
				eles = doc.select(div + "[class=p-name] a");
				if (eles != null) {
					for (Element element : eles) {
						JSONObject json = new JSONObject();
						href = element.attr("href");
						if (href != null && !href.startsWith("http:")) {
							href = "http:" + href;
						}
						doc = Jsoup.connect(href).timeout(10000)
								.header("User-Agent", header).get();
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
							eles = doc.select(div + "[id=name] h2");
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
							String src = eles.attr("src")
									.replace("/n0", "/imgzone")
									.replace("/n1", "/imgzone");
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
				eles = doc.select("p[class=productTitle] a");
				for (Element element : eles) {
					JSONObject json = new JSONObject();
					String src1 = element.attr("href");
					if (src1 != null && src1.trim().length() > 0
							&& !src1.startsWith("https")) {
						href = "https:" + src1;
					}
					if (href != null && href.trim().length() > 0) {
						doc = Jsoup.connect(href).timeout(10000)
								.header("User-Agent", header).get();
						// 获取基本信息
						// 获取商品名称
						eles = doc.select(div + "[class=tb-detail-hd] " + h);
						if (eles != null) {
							String productName = eles.text().trim();
							if (productName != null
									&& productName.trim().length() > 0) {
								json.put("productName", productName);
							}
						}
					}
					// 抓取图片
					eles = doc.select(img + "[id=J_ImgBooth]");
					if (eles != null && !eles.isEmpty()) {
						src1 = eles.attr("src");
						String pictureType = src1.substring(
								src1.lastIndexOf("."), src1.length());
						href = src1.substring(0, src1.indexOf(pictureType, 0))
								+ pictureType;
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

}
