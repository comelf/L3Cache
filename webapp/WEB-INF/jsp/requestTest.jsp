<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Request Test</title>
</head>
<body>
Request Test!<br>
<div id="searchBar">
검색어 <input type="text" name="query"><br>
Page  <input type="text" name="start"><br>
<input type="submit" name="search" value="검색"><br>
</div>
<div id="response"></div>

<section class="itemLists">
					<h1>목록</h1>
					<article>
						<table class="itemTable">
							<thead>
								<tr>
									<th>img</th>
									<th>title</th>
									<th>lprice</th>
									<th>mallName</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</article>
				</section>




	<script id="text/javascript">
		var Test = {
			init : function() {
				var searchDiv = document.getElementById("searchBar");
				var searchBtn = searchDiv.children[4];
				searchBtn.addEventListener("click", Test.searchClickEvent.bind(this), false);
			},
			search : function(query, start){
				var enQuery = escape(encodeURIComponent(query));
				var request = new XMLHttpRequest();
				var path="http://localhost:8080/search/naver/shop/cache";
				var params="?query='"+enQuery +"&display=20&start="+start+"&sort=sim";
				
				
				request.open("POST", path+params, true);
				request.setRequestHeader("Content-Type","application/json");
				
				request.send(null);
				request.onreadystatechange = function() {
					if (request.readyState==4){
						var result = JSON.parse(request.response);
						if(result.response.result==1){
							var body = document.getElementById('response');
							body.innerText = "검색결과 없음";
						}
						if(result.response.result==0){
							var body = document.getElementById('response');
							var elSection = document.querySelector(".itemLists");
							var elTableBody = elSection.querySelector("tbody");						
							
							body.innerText = "result : " + result.response.result + ",  total : " + result.response.total;
						  	
							var itemList = result.response.itemList
						  	var length = itemList.length;
							
							var tableSize = elTableBody.rows.length;
							if(tableSize>1){
								var itemTable = elSection.querySelector("table");
								for(var i = 0; i<tableSize; i++){
									itemTable.deleteRow(1);	
								}
							}
							
							for (var i = 0; i < length; i++) {
								var newRow = elTableBody.insertRow(elTableBody.rows.length);
								
								Test.insertImageRow(newRow, 0, itemList[i].image);
								Test.insertRow(newRow, 1, itemList[i].title);
								Test.insertRow(newRow, 2, itemList[i].lprice);
								Test.insertRow(newRow, 3, itemList[i].mallName);
								newRow.dataset.link = itemList[i].link;
								newRow.addEventListener("click", Test.rowClickEvent.bind(this), false);
								newRow.addEventListener("mouseover", Test.rowMouseoverEvent.bind(this), false);
								newRow.addEventListener("mouseout", Test.rowMouseoutEvent.bind(this), false);
							}
							
					    }
					}
				}
			},
			
			rowClickEvent : function(e){
				console.log(e.target.parentNode.dataset.link);	
			},
			
			rowMouseoverEvent : function(e){
				e.target.parentNode.style.background = '#dddddd';;
			},
			
			rowMouseoutEvent : function(e){
				e.target.parentNode.style.background = '#ffffff';;
			},
			
			searchClickEvent : function(e){
				var div = e.target.parentNode;
				var query = div.children[0].value;
				var start = div.children[2].value;
				if(query==""){
					alert("검색어를 입력해 주세요.");
					return;
				};
				if(start==""){
					start=1;
				};
			
				var eCheck=/^[_0-9+]*$/i;
				var num_check = eCheck.test(start);
				
				if(num_check!=true){
					alert("페이지는 숫자만 가능합니다.");
					return;;
				};
			
				this.search(query,start);
			},
			
			insertRow : function(row, cellNum, text) {
				var newCell = row.insertCell(cellNum);
				var newText = document.createTextNode(text)
				newCell.appendChild(newText);
			},
			
			insertImageRow : function(row, cellNum, text) {
				var newCell = row.insertCell(cellNum);
				var newImg = document.createElement('img');
				newImg.src = text;
				newImg.width = '100';
				newImg.height = '100';
				newCell.appendChild(newImg);
			},
			
		}

		
		
		Test.init();
	</script>
</body>
</html>