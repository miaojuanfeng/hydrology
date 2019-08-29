$('body').height($('body')[0].clientHeight);
			let headImg,inviteCode;
			let timer = null;
			$(function() {
				UrlSearch();
				getNodeTime();
				$("#phoneCode").on('click', function() {
					sendCode();
				})

				$("#zhuceBtn").on('click', function() {
					register();
				})
			})

			function UrlSearch() {
				var name, value;
				var str = location.href;
				var num = str.indexOf("?");
				var urlValue = [];
				str = str.substr(num + 1);

				var arr = str.split("&");

				for(var i = 0; i < arr.length; i++) {
					num = arr[i].indexOf("=");
					if(num > 0) {
						name = arr[i].substring(0, num);
						value = arr[i].substr(num + 1);
						this[name] = value;
						urlValue.push(value);

					}
				}

				headImg = urlValue[0];
				inviteCode = urlValue[1];
				if(headImg != null){
					$('.headImg').attr('src',headImg);
				}				
			}
			
			function getNodeTime(){
				$.ajax({
					type: "post",
					url: "/VTB_CHAIN/api/user/getTime",
					async: false,
					success:function(data){
						
						if(data.code == 200){
							//存入缓存
							localStorage.setItem('sysTime',data.data.sysTime);
							//获取本机时间
							var timeSelf = new Date().getTime();
							
							localStorage.setItem('timeSelf',timeSelf);
						}else {
							localStorage.setItem('sysTime',null);
							//获取本机时间
							var timeSelf = new Date().getTime();
							
							localStorage.setItem('timeSelf',timeSelf);
						}
					},
					error:function(error){						
						
						localStorage.setItem('sysTime',null);
						//获取本机时间
						var timeSelf = new Date().getTime();
						
						localStorage.setItem('timeSelf',timeSelf);
					}
				})	
					
			}
			function sendCode() {

				//获取手机号
				let phone = $('#phone').val();
				//验证手机
				if(!(/^1(3|4|5|6|7|8)\d{9}$/.test(phone)) || phone == "") {
					$(".am-modal-bd").html('手机号格式错误');
					$('#my-alert').modal();

					setTimeout(function() {
						$('#my-alert').modal('close');
					}, 1000);
					return false;
				}
				clearInterval(timer); //这句话至关重要
				var time = 60;
				var that = $('#phoneCode'); //习惯
				timer = setInterval(function() {
					console.log(time)
					if(time <= 0) {
						that.val('');
						that.val("获取验证码");
						that.disabled = false;

					} else {
						that.val('');
						that.val(time + '秒');
						that.disabled = true;
						time--;
					}
				}, 1000);
				//计算时间
				if(localStorage.getItem('sysTime') ==null){
					$(".am-modal-bd").html('系统错误');
					$('#my-alert').modal();

					setTimeout(function() {
						$('#my-alert').modal('close');
					}, 1500);
				}
				var sysTime = parseInt(localStorage.getItem('sysTime'));
				var timeSelf = parseInt(localStorage.getItem('timeSelf'));
				var nowTime = new Date().getTime();
				//计算差值
				var timeLast = nowTime - timeSelf;
				var timeSendCode = timeLast + sysTime;
				
				//签名
				var sign = SHA2('/VTB_CHAIN/api/user/getSmsCode'+ timeSendCode)
				//var sign = SHA2('123456789');
				
				
				$.ajax({
					type: "post",
					url: "/VTB_CHAIN/api/user/getSmsCode",
					async: false,
					data: {
						phoneNumber: phone,
						type: -1
					},
					headers:{
						stm:timeSendCode,
						sign:sign
					},
					success: function(data) {
						console.log(data);
						if(data.code == 200) {
							$(".am-modal-bd").html(data.msg);
							$('#my-alert').modal();

							setTimeout(function() {
								$('#my-alert').modal('close');
							}, 1000);

						} else {
							$(".am-modal-bd").html(data.msg);
							$('#my-alert').modal();

							setTimeout(function() {
								$('#my-alert').modal('close');
							}, 1500);
						}
					},
					error: function(error) {
						console.log(error);
						$(".am-modal-bd").html('服务器错误');
						$('#my-alert').modal();

						setTimeout(function() {
							$('#my-alert').modal('close');
						}, 1000);
					}
				});

			}

			//注册
			function register() {

				//获取手机号
				let phone = $('#phone').val();
				//验证手机
				if(!(/^1(3|4|5|6|7|8)\d{9}$/.test(phone)) || phone == "") {
					$(".am-modal-bd").html('手机号格式错误');
					$('#my-alert').modal();

					setTimeout(function() {
						$('#my-alert').modal('close');
					}, 1000);
					return false;
				}

				//获取密码
				let passWord = $('#passWord').val();

				//验证密码
				if(passWord == "") {
					$(".am-modal-bd").html('请输入密码');
					$('#my-alert').modal();

					setTimeout(function() {
						$('#my-alert').modal('close');
					}, 1000);
					return false;
				}

				//获取验证码
				let keyCode = $('#keyCode').val();

				//验证验证码
				if(keyCode == "") {
					$(".am-modal-bd").html('请输入验证码');
					$('#my-alert').modal();

					setTimeout(function() {
						$('#my-alert').modal('close');
					}, 1000);
					return false;
				}
				
				//计算时间
				if(localStorage.getItem('sysTime') ==null){
					$(".am-modal-bd").html('系统错误');
					$('#my-alert').modal();

					setTimeout(function() {
						$('#my-alert').modal('close');
					}, 1500);
				}
				var sysTime = parseInt(localStorage.getItem('sysTime'));
				var timeSelf = parseInt(localStorage.getItem('timeSelf'));
				var nowTime = new Date().getTime();
				//计算差值
				var timeLast = nowTime - timeSelf;
				var timeStamp = timeLast + sysTime;

				//签名
				var sign = SHA2('/VTB_CHAIN/api/user/htm/register'+ timeStamp)
				//var sign = SHA2('123456789');
				//rsa加密
				const key = "MIHfMA0GCSqGSIb3DQEBAQUAA4HNADCByQKBwQCTC2VHkWEs1BrnFalIJoYH0Qh0NufwQkVyLIw3L695kblAHok5LlYABdHCsxmzJfoUiY+wN40V1PJMQh2nRnRWGKs8AwxCx1fQAb+nMkfIqOzgsDb5dFGAkZ9LQvIgzkRZTIf3KG9Ya7lpvk79Pv2/prxGLT8HawuAHH+nfaBDhOzmTjqXVDZ34pKKyO5nC3BEEIaEXbGOMLP7KSmZkAGg72/mczDxHPXX9UNMp0K5m9dEsjcrqXiXGGzlxTrSoJECAwEAAQ==";
				let encrypt = new JSEncrypt();
				encrypt.setPublicKey(key);
				var jsonMsg = {
					'passWord':passWord
				};

				let encryptKey = encrypt.encrypt(JSON.stringify(jsonMsg));
								
								
				$.ajax({
					type: "post",
					url: "/VTB_CHAIN/api/user/htm/register",
					async: false,
					data: {
						phoneNumber: phone,
						smsCode: keyCode,
						data: encryptKey,
						inviteCode: inviteCode
					},
					headers: {
						stm: timeStamp,
						sign: sign
					},
					success: function(data) {
						if(data.code == 200) {
							$(".am-modal-bd").html(data.msg);
							$('#my-alert').modal();

							setTimeout(function() {
								$('#my-alert').modal('close');
								//跳转下载
								window.location.href = "https://lh.saintenjoy.com/download"
							}, 1500);

						} else {
							var d = JSON.parse(data);							
							$(".am-modal-bd").html(d.msg);
							$('#my-alert').modal();
							setTimeout(function() {
								$('#my-alert').modal('close');
								//跳转下载
								//window.location.href = "https://lh.saintenjoy.com/download"
							}, 1500);

						}
					},
					error: function(error) {
						$(".am-modal-bd").html('服务器错误');
						$('#my-alert').modal();

						setTimeout(function() {
							$('#my-alert').modal('close');
						}, 1000);
					}
				});
			}