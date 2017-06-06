	$(document).ready(function(){			
			var $li = $('#navlist li');
			var $ul = $('.navMessage>ul');
			var navMessage=$('.navMessage');
			var listbarLeft=$('.listbar-left');
			var listbarRight=$('.listbar-right');
			var inputs=$('.list1 input');
			
			$li.click(function(){
				var $this = $(this);
				var $t = $this.index();
				$(".navMessage").css("display","block");
				if($ul.eq($t).hasClass('show')){
					$this.children('span').removeClass('')
										.addClass('');
					$ul.eq($t).removeClass('show');					
				}else{
					$this.children('span').removeClass('')
										.addClass('');
					$ul.eq($t).addClass('show').siblings().removeClass('show');
				};
//添加遮罩层				
				if($('.navMessage>ul').hasClass('show')){
					$('.message').addClass('mask');
				}else{
					$('.message').removeClass('mask');
				}	
			});
			$('.message').click(function(){
				$ul.removeClass('show');
			});
			


			//去看看弹出
			$('.btn-look').click(function(){
				$('.referring').css({
					display:'block'
				});
			});
			$('.btn-close').click(function(){
				$('.referring').css({
					display:'none'
				});
			});
			//编辑
			$('.btn-finished').click(function(){
				inputs.prop("checked","");
				$('.form1').css({
					display:'none'
				});
				$('.form2').css({
					display:'block'
				});
				$('.btn-finished').css({
					display:'none'
				});
				$('.btn-ok').css({
					display:'block'
				});
			});
			//完成
			$('.btn-ok').click(function(){
				inputs.prop("checked","");
				$('.form1').css({
					display:'block'
				});
				$('.form2').css({
					display:'none'
				});
				$('.btn-finished').css({
					display:'block'
				});
				$('.btn-ok').css({
					display:'none'
				});
			});
			//全选		
			$('#allCheck').click(function(){
				if($("#allCheck").is(":checked")){					
					inputs.prop("checked","checked");
				}else{
					inputs.prop("checked","");
				};
			});
			$('#allCheck2').click(function(){
				if($("#allCheck2").is(":checked")){					
					inputs.prop("checked","checked");
				}else{
					inputs.prop("checked","");
				};
			});
			//删除
			//弹出框
//			document.getElementById("confirmBtn").addEventListener('tap', function() {
//				var btnArray = ['否', '是'];
//				mui.confirm('即将删除收藏，确认？', '你好用户', btnArray, function(e) {
//					if (e.index == 1) {
//						if (inputs.is(":checked")){
//							$("input[name='checkbox1']:checked").parents('.listStyle').remove();
//						}
//					} else {
//												
//					}
//				})
//			});
    });
