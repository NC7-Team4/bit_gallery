document.addEventListener('DOMContentLoaded', function () {
    var tid = 0;
    var p = document.getElementById("RemainPeriod");
    if (p) {
        var period = parseInt(p.textContent);

        function auctionClock() {
            period -= 1;
            if (period < 0) {
                if (tid) clearTimeout(tid);
                window.setTimeout(function () {
                    location.reload();
                }, 1000);
                return;
            }

            var strTime = "";
            var day = parseInt(period / (24 * 60 * 60));
            var hour = parseInt((period / 60 / 60) % 24);
            var minute = parseInt((period / 60) % 60);
            var second = parseInt(period % 60);

            if (day > 0) {
                strTime = day + "일 ";
            }
            strTime = strTime + hour + "시간 " + minute + "분 " + second + "초";

            if (p) p.innerHTML = strTime;
            if (tid) clearTimeout(tid);
            tid = window.setTimeout(auctionClock, 1000);
        }

        if (period > 0) auctionClock();
    }
});
