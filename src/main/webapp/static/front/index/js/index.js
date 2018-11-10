$(function() {
	var curWwwPath=window.document.location.href;
	var pathName=window.document.location.pathname;
	var pos=curWwwPath.indexOf(pathName);
	var localhostPaht=curWwwPath.substring(0,pos);
	var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	var realPath=localhostPaht+projectName;
    init();
    // 滚动页面，一次滚动一整个页面	
    $("html,body").mousewheel(function(e) {
    	//如果当前正在执行动画则返回，避免多次执行动画
    	if($("html,body").is(":animated")) return;
    	//获取滚动前激活元素在父元素中的索引值    	
        var index=$(".siderBar .option").index($(".siderBar .option.optionFocus"));
        // 根据鼠标滚轮上滚或下滚修改index索引值
        if (-1 == e.deltaY) {
            index++;
        } else if (1 == e.deltaY) {
        	index--;
        }
        if(index>=0 && index<=5){
        // 移除获得焦点的样式
            $(".siderBar .option").removeClass("optionFocus");
            // 根据index值为特定元素添加获得焦点样式
            $(".siderBar .option:nth-of-type(" + index+ ")").addClass("optionFocus");
            $(".siderBar .option .circle").eq(index).trigger("click");
        }
       /* 	$("#highlight"+(scrollTop/1000)+">h2").css("animation","slideToRight 0.7s linear");
            	$("#highlight"+(scrollTop/1000)+">p").css("animation","slideToRight 0.7s 0.7s linear forwards")
        }*/
        })
    // 右侧导航栏点击切换页面监听
    $(".siderBar .option .circle").click(function() {
    	//获取点击对象在父元素中的索引位置
        var index=$(".siderBar .option").index($(this).parent());
        $("html,body").stop(true, true);
        $("html,body").animate({
            'scrollTop': index * $(window).innerHeight()//index+"000" 
        }, 400);
        // 移除获得焦点的样式
        $(".siderBar .option").removeClass("optionFocus");
        // 为点击元素添加获得焦点样式
        $(".siderBar .option:nth-of-type("+(index+1)+")").addClass("optionFocus");
    })
//    //菜单选择页面鼠标移动菜单跟随少幅度的变形，实现透视效果
//    $(".selectMenu").mousemove(function(e) {
//        var x = e.pageX - 900;
//        var y = -e.pageY + 4500;
//        $(".menuContainer .menu").css("transform", "rotateY(" + (x / 200) + "deg) rotateX(" + (y / 200) + "deg)");
//    })

   /* for(var i=0;i<$(".littleBall").length;i++){
    	$(".littleBall:eq("+i+")").css("transform","rotateX(90deg) rotateY("+(i*30)+"deg) translateZ(240px)");
    }*/
    $(".menu").click(function (){
  	  if($(this).attr("position") == "left"){
  		  $(this).css("animation","menuLeftToFront 1.6s linear 1 forwards").attr("position","front")
  		  .siblings("[position='front']").css("animation","menuFrontToRight 0.8s linear 1 forwards").attr("position","right")
  		  .siblings("[position='right']").css("animation","menuRightToLeft 0.8s linear 1 forwards").attr("position","left");
  		  
  	  }else if($(this).attr("position") == "right"){
  		  $(this).css("animation","menuRightToFront 1.6s linear 1 forwards").attr("position","front")
  		  .siblings("[position='front']").css("animation","menuFrontToLeft 0.8s linear 1 forwards").attr("position","left")
  		  .siblings("[position='left']").css("animation","menuLeftToRight 0.8s linear 1 forwards").attr("position","right");
  	  }
  	$(this).children(".ball").addClass("focusBall");
  	$(this).siblings().children(".ball").removeClass("focusBall");
    });
    function turnTo(){
		var id=arguments[0].currentTarget.id;
		switch(id){
		case "menu1":
			location.assign(realPath+"/nation/front/");
			break;
		case "menu2":
			location.assign(realPath+"/campus/front/");
			break;
		case "menu3":
			var localStorage=window.localStorage; 
			var token = localStorage.getItem("token");
			var userId = localStorage.getItem("userId");
			location.assign(realPath+"/interest/index/?token="+token+"&userId="+userId);
			break;
		}
	}
    $(".menu[position=front]").click(turnTo);
	$(".menu").click(function () {
		$(".menu").unbind("click",turnTo);
		$(".menu[position=front]").click(turnTo);
	});
    //介绍页面切换监听
    $(".introduction>div").click(function () {
        var thisFunction=arguments.callee;
        //解除绑定点击监听事件，防止多次点击出现动画播放混乱
        $(".introduction>div").unbind("click");
        var introPages = $(".introPage"),
            introduction = $(".introduction");
        if($(this)[0]==introPages.eq(1)[0]){
            var nextPage=$(this).next();
            nextPage.css({
                "animation-name":"RotateToBack",
                "animation-duration":"1s",
                "animation-iteration-count":"1",
                "animation-fill-mode":"forward"
            });
            introduction.prepend(nextPage);
            setTimeout(function () {
                nextPage.removeAttr("style");
                //重新绑定点击监听事件
                $(".introduction>div").bind("click",thisFunction);
            },1100);
        }
        if($(this)[0]==introPages.eq(0)[0]){
            var thisOne=$(this);

            thisOne.css({
                "animation-name":"RotateToFront",
                "animation-duration":"1s",
                "animation-iteration-count":"1",
                "animation-fill-mode":"forward"
            });

            introduction.append(thisOne);
            setTimeout(function () {
                thisOne.removeAttr("style");
                //重新绑定点击监听事件
                $(".introduction>div").bind("click",thisFunction);
            },1100);
        }
    });
    
})
//初始化函数
function init() {
	//获取当前scrollTop值，再根据该值设置侧边栏相应获得焦点样式
	var currentScrollTop = $(window).scrollTop();
	var winHeight=$(window).innerHeight();
	 if (currentScrollTop == 0*winHeight || currentScrollTop == 1*winHeight || currentScrollTop == 2*winHeight|| currentScrollTop == 3*winHeight|| currentScrollTop == 4*winHeight|| currentScrollTop == 5*winHeight) {
         // 移除获得焦点的样式
         $(".siderBar .option").removeClass("optionFocus");
         // 根据scrollTop值为特定元素添加获得焦点样式
         $(".siderBar .option:nth-of-type(" + (currentScrollTop / winHeight + 1) + ")").addClass("optionFocus");
     }

		 //根据当前浏览器窗口设置所有页面总高度及每个页面高度
		$("body").css("height",6*winHeight);
		$("body>div:not(.navigation,.siderBar)").css("height",winHeight);
}

//浏览器窗口高度改变，页面高度跟随改变
$(window).resize(function(){
	init();
	//获取当前浏览器窗口高度
	var winHeight=$(window).innerHeight();
	var index=$(".siderBar .option").index($(".siderBar .option.optionFocus"));
	//根据浏览器高度更改相应滚动值
	$(window).scrollTop(index * winHeight);
	 //根据当前浏览器窗口设置所有页面总高度及每个页面高度
	$("body").css("height",6*winHeight);
	$("body>div:not(.navigation,.siderBar)").css("height",winHeight);
})