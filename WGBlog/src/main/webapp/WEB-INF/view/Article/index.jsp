<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>文章 - 博客管理后台</title>
<link rel="stylesheet" type="text/css" href="/WGBlog/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/WGBlog/css/back/style1.css">
<link rel="stylesheet" type="text/css" href="/WGBlog/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="icon" href="/WGBlog/images/favicon.ico" type="image/x-icon"/>
<%--layui--%>
<link href="/WGBlog/layui/css/layui.css" rel="stylesheet" type="text/css" />
<script src="/WGBlog/js/jquery/jquery-2.1.1.min.js"></script>
<script src="/WGBlog/layui/layui.js" type="text/javascript"></script>
<%--导入分页插件--%>
<link href="/WGBlog/js/jquery/bs_pagination/jquery.bs_pagination.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/WGBlog/js/jquery/bs_pagination/en.js"></script>
<script type="text/javascript" src="/WGBlog/js/jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
</head>

<body class="user-select">
<section class="container-fluid">
  <header>
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false"> <span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
            <a class="navbar-brand" href="/">AndyW</a> </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="">消息 <span class="badge"></span></a></li>
            <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">王兆刚 <span class="caret"></span></a>
              <ul class="dropdown-menu dropdown-menu-left">
                <li><a title="查看或修改个人信息" data-toggle="modal" data-target="#seeUserInfo">个人信息</a></li>
                <li><a title="查看您的登录记录" data-toggle="modal" data-target="#seeUserLoginlog">登录记录</a></li>
              </ul>
            </li>
            <li><a href="login.html" onClick="if(!confirm('是否确认退出？'))return false;">退出登录</a></li>
            <li><a data-toggle="modal" data-target="#WeChat">帮助</a></li>
          </ul>
          <form action="" method="post" class="navbar-form navbar-right" role="search">
            <div class="input-group">
              <input type="text" class="form-control" id="title" autocomplete="off" placeholder="键入关键字搜索" maxlength="15">
              <span class="input-group-btn">
              <button class="btn btn-default" id="search" type="button">搜索</button>
              </span> </div>
          </form>
        </div>
      </div>
    </nav>
  </header>
  <div class="row">
    <aside class="col-sm-3 col-md-2 col-lg-2 sidebar">
      <ul class="nav nav-sidebar">
        <li><a href="index.html">报告</a></li>
      </ul>
      <ul class="nav nav-sidebar">
        <li class="active"><a href="article.html">文章</a></li>
        <li><a href="notice.html">公告</a></li>
        <li><a href="comment.html">评论</a></li>
        <li><a data-toggle="tooltip" data-placement="top" title="网站暂无留言功能">留言</a></li>
      </ul>
      <ul class="nav nav-sidebar">
        <li><a href="category.html">栏目</a></li>
        <li><a class="dropdown-toggle" id="otherMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">其他</a>
          <ul class="dropdown-menu" aria-labelledby="otherMenu">
            <li><a href="flink.html">友情链接</a></li>
            <li><a href="loginlog.html">访问记录</a></li>
          </ul>
        </li>
      </ul>
      <ul class="nav nav-sidebar">
        <li><a class="dropdown-toggle" id="userMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">用户</a>
          <ul class="dropdown-menu" aria-labelledby="userMenu">
            <li><a href="#">管理用户组</a></li>
            <li><a href="manage-user.html">管理用户</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="loginlog.html">管理登录日志</a></li>
          </ul>
        </li>
        <li><a class="dropdown-toggle" id="settingMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">设置</a>
          <ul class="dropdown-menu" aria-labelledby="settingMenu">
            <li><a href="setting.html">基本设置</a></li>
            <li><a href="readset.html">用户设置</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">安全配置</a></li>
            <li role="separator" class="divider"></li>
            <li class="disabled"><a>扩展菜单</a></li>
          </ul>
        </li>
      </ul>
    </aside>
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-lg-10 col-md-offset-2 main" id="main">
      <form action="/Article/checkAll" method="post" >
        <h1 class="page-header">操作</h1>
        <ol class="breadcrumb">
          <li><a href="/WGBlog/toView/view/Article/addArticle">增加文章</a></li>
        </ol>
        <h1 class="page-header">管理 <span class="badge" id="count"></span></h1>
        <div class="table-responsive">
          <table class="table table-striped table-hover">
            <thead>
              <tr>
                <th>
                    <input type="checkbox" class="input-control" name="checkbox" id="father" />
                </th>
                <th><span class="glyphicon glyphicon-file"></span> <span class="visible-lg">标题</span></th>
                <th><span class="glyphicon glyphicon-list"></span> <span class="visible-lg">栏目</span></th>
                <th class="hidden-sm"><span class="glyphicon glyphicon-tag"></span> <span class="visible-lg">标签</span></th>
                <th class="hidden-sm"><span class="glyphicon glyphicon-comment"></span> <span class="visible-lg">点赞</span></th>
                <th><span class="glyphicon glyphicon-time"></span> <span class="visible-lg">日期</span></th>
                <th><span class="glyphicon glyphicon-pencil"></span> <span class="visible-lg">操作</span></th>
              </tr>
            </thead>
            <tbody id="articleBody">
            </tbody>
          </table>
        </div>
        <footer class="message_footer">
          <nav>
              <%--分页插件--%>
              <div  style="height: 50px; position: relative;top: 30px;">
                  <div id="activityPage"></div>
              </div>
          </nav>
        </footer>
      </form>
    </div>
  </div>
</section>
<!--个人信息模态框-->
<div class="modal fade" id="seeUserInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <form action="" method="post">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" >个人信息</h4>
        </div>
        <div class="modal-body">
          <table class="table" style="margin-bottom:0px;">
            <thead>
              <tr> </tr>
            </thead>
            <tbody>
              <tr>
                <td wdith="20%">姓名:</td>
                <td width="80%"><input type="text" value="王兆刚" class="form-control" name="truename" maxlength="10" autocomplete="off" /></td>
              </tr>
              <tr>
                <td wdith="20%">用户名:</td>
                <td width="80%"><input type="text" value="admin" class="form-control" name="username" maxlength="10" autocomplete="off" /></td>
              </tr>
              <tr>
                <td wdith="20%">电话:</td>
                <td width="80%"><input type="text" value="18538078281" class="form-control" name="usertel" maxlength="13" autocomplete="off" /></td>
              </tr>
              <tr>
                <td wdith="20%">旧密码:</td>
                <td width="80%"><input type="password" class="form-control" name="old_password" maxlength="18" autocomplete="off" /></td>
              </tr>
              <tr>
                <td wdith="20%">新密码:</td>
                <td width="80%"><input type="password" class="form-control" name="password" maxlength="18" autocomplete="off" /></td>
              </tr>
              <tr>
                <td wdith="20%">确认密码:</td>
                <td width="80%"><input type="password" class="form-control" name="new_password" maxlength="18" autocomplete="off" /></td>
              </tr>
            </tbody>
            <tfoot>
              <tr></tr>
            </tfoot>
          </table>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          <button type="submit" class="btn btn-primary">提交</button>
        </div>
      </div>
    </form>
  </div>
</div>
<!--个人登录记录模态框-->
<div class="modal fade" id="seeUserLoginlog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" >登录记录</h4>
      </div>
      <div class="modal-body">
        <table class="table" style="margin-bottom:0px;">
          <thead>
            <tr>
              <th>登录IP</th>
              <th>登录时间</th>
              <th>状态</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>::1:55570</td>
              <td>2016-01-08 15:50:28</td>
              <td>成功</td>
            </tr>
            <tr>
              <td>::1:64377</td>
              <td>2016-01-08 10:27:44</td>
              <td>成功</td>
            </tr>
            <tr>
              <td>::1:64027</td>
              <td>2016-01-08 10:19:25</td>
              <td>成功</td>
            </tr>
            <tr>
              <td>::1:57081</td>
              <td>2016-01-06 10:35:12</td>
              <td>成功</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">朕已阅</button>
      </div>
    </div>
  </div>
</div>
<!--提示模态框-->
<div class="modal fade user-select" id="areDeveloping" tabindex="-1" role="dialog" aria-labelledby="areDevelopingModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="areDevelopingModalLabel" style="cursor:default;">该功能正在日以继夜的开发中…</h4>
      </div>
      <div class="modal-body"> <img src="images/baoman/baoman_01.gif" alt="深思熟虑" />
        <p style="padding:15px 15px 15px 100px; position:absolute; top:15px; cursor:default;">很抱歉，程序猿正在日以继夜的开发此功能，本程序将会在以后的版本中持续完善！</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">朕已阅</button>
      </div>
    </div>
  </div>
</div>
<script src="/WGBlog/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script src="/WGBlog/js/admin-scripts.js"></script>
<script src="/WGBlog/js/layer-3.5.1/layer.js" type="text/javascript"></script>

<%--导入分页插件--%>
<link type="text/css" href="/WGBlog/js/bs_pagination/jquery.bs_pagination.min.css" />
<script src="/WGBlog/js/bs_pagination/en.js"></script>
<script src="/WGBlog/js/bs_pagination/jquery.bs_pagination.min.js"></script>
<script>
  refresh(1,2)
  //定义函数发送请求不同页码的异步请求
  function refresh(page,pageSize){
    $.post("/WGBlog/Article/list",{
      'page':page,
      'pageSize':pageSize,
        'title':$('#title').val()
    },function (data) {
        //设置当前文章数量
        $("#count").text(data.total + "篇文章");
      //清空原来的内容
        $("#articleBody").html("");
        var articles=data.list;
      for(var i =0 ; i < articles.length; i++){
        var article=articles[i];
        if(article.isOpen=="0"){
          $('#articleBody').append("<tr>\n" +
                  "                <td><input type=\"checkbox\" class=\"input-control\" name=\"checkbox\" value=\"\" /></td>\n" +
                  "                <td class=\"article-title\">"+article.title+"</td>\n" +
                  "                <td>"+article.cid+"</td>\n" +
                  "                <td class=\"hidden-sm\">"+article.tagNames+"</td>\n" +
                  "                <td class=\"hidden-sm\">"+article.thumbsUp+"</td>\n" +
                  "                <td>"+article.create_time+"</td>\n" +
                  "                <td>\n" +
                  "                    <a href=\"/WGBlog/toView/view/Article/updateArticle?id="+article.aid+"\">修改</a> <a onclick=\"deleteById('"+article.aid+"')\" href='javascript:void(0)'>删除</a>&nbsp;\n" +
                  "                    <input type=\"radio\" onclick=change(\'"+article.aid+"\',$(this).val()) name="+article.aid+" value=\"1\"/>公开 <input onclick=change(\'"+article.aid+"\',$(this).val()) type=\"radio\" checked name="+article.aid+" value=\"0\" />私密\n" +
                  "                </td>\n" +
                  "              </tr>");
        }else{
          $('#articleBody').append("<tr>\n" +
                  "                <td><input type=\"checkbox\" class=\"input-control\" name=\"checkbox\" value=\"\" /></td>\n" +
                  "                <td class=\"article-title\">"+article.title+"</td>\n" +
                  "                <td>"+article.cid+"</td>\n" +
                  "                <td class=\"hidden-sm\">"+article.tagNames+"</td>\n" +
                  "                <td class=\"hidden-sm\">"+article.thumbsUp+"</td>\n" +
                  "                <td>"+article.create_time+"</td>\n" +
                  "                <td>\n" +
                  "                    <a href=\"/WGBlog/toView/view/Article/updateArticle?id="+article.aid+"\">修改</a> <a onclick=\"deleteById('"+article.aid+"')\" >删除</a>&nbsp;\n" +
                  "                    <input checked type=\"radio\" onclick=change(\'"+article.aid+"\',$(this).val()) name="+article.aid+" value=\"1\"/>公开 <input onclick=change(\'"+article.aid+"\',$(this).val()) type=\"radio\"  name="+article.aid+" value=\"0\" />私密\n" +
                  "                </td>\n" +
                  "              </tr>");
        }
      }
        $("#activityPage").bs_pagination({
            currentPage: data.pageNum, // 页码
            rowsPerPage:data.pageSize, // 每页显示的记录条数
            maxRowsPerPage: 10, // 每页最多显示的记录条数
            totalPages: data.pages, // 总页数
            totalRows: data.total, // 总记录条数
            visiblePageLinks: 3, // 显示几个卡片
            showGoToPage: true,
            showRowsPerPage: true,
            showRowsInfo: true,
            showRowsDefaultInfo: true,
            //回调函数，用户每次点击分页插件进行翻页的时候就会触发该函数
            onChangePage : function(event, obj){
                //currentPage:当前页码 rowsPerPage:每页记录数
                refresh(obj.currentPage,obj.rowsPerPage);
            }
        },'json');
    },'json');
  }
  $("#search").click(function () {
refresh(1,2);
  })
//定义是否公开函数
  function change(aid,value) {
$.get("/WGBlog/Article/changeIsOpen",{
  'aid':aid,
  'isOpen':value
},function (data) {
layer.alert(data.mess,{icon:6 })
},'json');
  }

  //异步删除
  function deleteById(aid) {
    $.get("/WGBlog/Article/deleteArticle",{'id':aid},function (data) {
      if(data.ok){
        //删除成功
        layer.alert(data.mess, {icon:6});
        //刷新页面
        refresh(1,3);
      }
    },'json');
  }
</script>
</body>
</html>
