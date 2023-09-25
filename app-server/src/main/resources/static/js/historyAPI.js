//document.getElementById('loadBidList').addEventListener('click', function() {
//    document.getElementById('bidTable').style.display = 'table';
//    fetch('/history/api/bidList')
//    .then(response => response.json())
//    .then(data => {
//        const tbody = document.getElementById('historyList');
//        tbody.innerHTML = ""; // 내용 초기화
//        data.forEach((history, index) => {
//            const tr = document.createElement('tr');
//            tr.innerHTML = `
//                <td>${index + 1}</td>
//                <td>${history.article.title}</td>
//                <td>${history.price}</td>
//                <td>${history.winBid}</td>
//                <td>${new Date(history.bidDate).toISOString().replace('T', ' ').substring(0, 19)}</td>
//            `;
//            tbody.appendChild(tr);
//        });
//    })
//    .catch(error => {
//        console.error('Error:', error);
//        alert('입찰 내역을 불러오는 중 오류가 발생했습니다.');
//    });
//});

let currentUserId;  // Declare currentUserId at top level, so that it can be set from the HTML page.

// 환전 내역 함수 정의
function fetchHistoryData() {
    fetch('/exchange/api/bidList')
        .then(response => response.json())
        .then(data => {

            const tbody = document.getElementById('historyList');
            tbody.innerHTML = "";

            data.forEach((history, index) => {

                let row = `
                    <tr>
                        <td>${index + 1}</td>
                    </tr>`;
                tbody.innerHTML += row;
            });

            document.getElementById('historyTable').style.display = "table";
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

document.querySelector('a[href="#faq-cat-2"]').addEventListener('click', fetchHistoryData);
document.querySelector('a[data-parent="#accordion-cat-2"]').addEventListener('click', fetchHistoryData);