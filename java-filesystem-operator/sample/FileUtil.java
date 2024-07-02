package net.mingsoft.cms.util;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;


public class CmsParserUtil {

    public static void generateBasic(List<CategoryBean> articleIdList, String htmlDir,Date datetime) {


        Map<String, Object> parserParams = new HashMap<String, Object>();
        parserParams.put(ParserUtil.IS_DO, false);
        if (BasicUtil.getWebsiteApp() != null) {
            parserParams.put(ParserUtil.APP_DIR, BasicUtil.getWebsiteApp().getAppDir());
            parserParams.put(ParserUtil.URL, BasicUtil.getWebsiteApp().getAppHostUrl());
            parserParams.put(ParserUtil.APP_ID, BasicUtil.getWebsiteApp().getAppId());
        } else {
            parserParams.put(ParserUtil.URL, BasicUtil.getUrl());
            parserParams.put(ParserUtil.APP_DIR, BasicUtil.getApp().getAppDir());
        }

        parserParams.put(ParserUtil.HTML, htmlDir);


        String contextPath = BasicUtil.getContextPath();
        if (StringUtils.isNotBlank(contextPath) && "/".equalsIgnoreCase(contextPath) ){
            contextPath = "";
        }
        parserParams.putIfAbsent(ParserUtil.CONTEXT_PATH, contextPath);


        Map<Object, Object> contentModelMap = new HashMap<Object, Object>();
        ModelEntity contentModel = null;
        List<String> generateIds = new ArrayList<>();

        for (int artId = 0; artId < articleIdList.size(); ) {


            CategoryBean categoryBean = articleIdList.get(artId);
            String writePath = null;
            PageBean page = new PageBean();
            String articleId = categoryBean.getArticleId();
            String articleColumnPath = categoryBean.getCategoryPath();
            String categoryParentId = categoryBean.getId();
            if (StringUtils.isNotBlank(categoryBean.getCategoryParentIds())) {
                categoryParentId += ',' + categoryBean.getCategoryParentIds();
            }
            String columnUrl = categoryBean.getCategoryUrl();
            LOG.debug("columnUrl {}",columnUrl);
            String columnContentModelId = null;
            if (StringUtils.isNotBlank(articleIdList.get(artId).getMdiyModelId()) && StringUtils.isNotBlank(categoryBean.getMdiyModelId())) {
                columnContentModelId = categoryBean.getMdiyModelId();
            }

            if (generateIds.contains(articleId)) {
                artId++;
                continue;
            }

            if (!FileUtil.exist(ParserUtil.buildTemplatePath(columnUrl)) || categoryBean.getId() == null || categoryBean.getCategoryType() == null) {
                artId++;
                continue;
            }
            // 将
            generateIds.add(articleId);
            if (categoryBean.getCategoryType().equals(CategoryTypeEnum.COVER.toString())) {
                writePath = ParserUtil.buildHtmlPath(articleColumnPath + File.separator + ParserUtil.INDEX, htmlDir, parserParams.get(ParserUtil.APP_DIR).toString());
            } else {
                writePath = ParserUtil.buildHtmlPath(articleColumnPath + File.separator + articleId, htmlDir, parserParams.get(ParserUtil.APP_DIR).toString());
            }


            parserParams.put(ParserUtil.COLUMN, categoryBean);


            if (columnContentModelId != null) {
                if (contentModelMap.containsKey(columnContentModelId)) {
                    parserParams.put(ParserUtil.TABLE_NAME, contentModel.getModelTableName());
                } else {
                    contentModel = (ModelEntity) SpringUtil.getBean(IModelBiz.class)
                            .getById(columnContentModelId);
                    if (null!=contentModel){
                        contentModelMap.put(columnContentModelId, contentModel.getModelTableName());
                        parserParams.put(ParserUtil.TABLE_NAME, contentModel.getModelTableName());
                    }
                }
            }

            parserParams.put(ParserUtil.ID, articleId);

            if (artId > 0) {
                CategoryBean preCaBean = articleIdList.get(artId - 1);
                page.setPreId(preCaBean.getArticleId());
            }

            if (artId + 1 < articleIdList.size()) {
                CategoryBean nextCaBean = articleIdList.get(artId + 1);
                page.setNextId(nextCaBean.getArticleId());
            }
            if (datetime != null && categoryBean.getContentUpdateDate().before(datetime)){
                artId++;
                continue;
            }

            parserParams.put(ParserUtil.PAGE, page);
            String finalWritePath = writePath;
            HashMap<Object, Object> cloneMap = MapUtil.newHashMap();
            cloneMap.putAll(parserParams);
            HttpServletRequest request = SpringUtil.getRequest();
            String content = null;
            try {
                content = ParserUtil.rendering(columnUrl, cloneMap);
                FileUtil.writeString(content, finalWritePath, Const.UTF8);
            } catch (IOException e) {
                e.printStackTrace();
            }
            artId++;
        }
    }
}
