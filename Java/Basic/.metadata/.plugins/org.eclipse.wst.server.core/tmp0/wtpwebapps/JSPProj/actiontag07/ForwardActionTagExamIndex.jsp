<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ForwardActionTagExamIndex.jsp -->
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>&lt;jsp:forward&gt;액션 태그 예제</h1>            
        </div><!--jumbotron-->
        <form action="ForwardActionTagExamProcess.jsp" method="post">
			<div class="form-group">
				<label for="username">아이디</label> <input type="text"
					class="form-control" placeholder="아이디를 입력하세요" id="username"
					name="username">
			</div>
			<div class="form-group">
				<label for="password">비밀번호</label> <input type="password"
					class="form-control" placeholder="비밀번호를 입력하세요" id="password"
					name="password">
			</div>
			<div>
				<label>운동종목</label>
				<div class="form-check-inline">
					<label class="form-check-label"> <input type="checkbox"
						class="form-check-input" name="sports" value="축구">축구
					</label>
				</div>
				<div class="form-check-inline">
					<label class="form-check-label"> <input type="checkbox"
						class="form-check-input" name="sports" value="야구">야구
					</label>
				</div>
				<div class="form-check-inline">
					<label class="form-check-label"> <!--반드시 disabled는 속성으로-->
						<input type="checkbox" class="form-check-input" name="sports"
						value="농구">농구
					</label>
				</div>
			</div>
			<div>
				<label>연령대</label>
				<div class="form-check-inline">
					<label class="form-check-label"> <input type="radio"
						class="form-check-input" name="ages" value="20대">20대
					</label>
				</div>
				<div class="form-check-inline">
					<label class="form-check-label"> <input type="radio"
						class="form-check-input" name="ages" value="30대">30대
					</label>
				</div>
				<div class="form-check-inline">
					<label class="form-check-label"> <!--반드시 disabled는 속성으로-->
						<input type="radio" class="form-check-input" name="ages"
						value="40대">40대
					</label>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">확인</button>
		</form>      
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>
    