document.addEventListener('DOMContentLoaded', function () {
    var purchaseButton = document.getElementById('purchaseButton');

    if (purchaseButton) {
        purchaseButton.addEventListener('click', function () {
            var articleNo = parseInt(
                document.getElementById('articleNo').textContent.replace(/\D/g, '')
            );
            var currentPage = parseInt(
                document.getElementById('userPoints').textContent.replace(/\D/g, '')
            );
            var purchasePrice = parseInt(
                document.getElementById('endPrice').textContent.replace(/\D/g, '')
            );

            var userPoints = parseInt(
                document.getElementById('userPoints').textContent.replace(/\D/g, '')
            );

            var userNoElement = document.getElementById('userNo');
            var articleNoElement = document.getElementById('articleNo');

            var userNo = parseInt(userNoElement.textContent);
            var articleNo = parseInt(articleNoElement.textContent);

            var confirmation = confirm(
                '즉시구매가격: ' + purchasePrice + '\n현재 포인트: ' + userPoints + '\n\n정말 즉시구매 하시겠습니까?'
            );

            function redirectToArticleDetail(articleNo) {
                var redirectURL = '/article/detail?articleNo=' + articleNo + '&path=0&currentPage=' + currentPage;

            }

            if (confirmation) {
                if (userPoints < purchasePrice) {
                    return alert("포인트가 부족합니다.");
                }

                // AJAX 요청
                $.ajax({
                    url: '/points/purchasePoint', // 즉시구매 API 엔드포인트 URL
                    method: 'POST',
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    async: false,
                    data: JSON.stringify({
                        userNo: userNo,
                        articleNo: articleNo,
                        bidAmount: purchasePrice,
                    }),
                    success: function (response) {
                        console.log(response);
                        if (response === true) {
                            alert("즉시구매가 완료되었습니다.");
                            window.location.href = redirectURL;
                        } else {
                            alert("즉시구매가 실패했습니다.");
                        }
                    }
                });
            }
        });
    }
});
