<a id="readme-top"></a>

<br />
<div align="center">
  <img src="https://hcmut.edu.vn/img/nhanDienThuongHieu/01_logobachkhoasang.png" alt="Logo" width="30%" height="30%">
  <h2 align="center">ĐỒ ÁN ĐA NGÀNH HK242 - YoloFarm Smart Dashboard</h2>
  <p align="center">
    Dự án phát triển hệ thống dashboard giám sát và điều khiển nông trại thông minh sử dụng cảm biến và WebSocket.
    <br />
    <a href="https://github.com/yolofarm-smart/dashboard">View Demo</a>
    ·
    <a href="https://github.com/yolofarm-smart/dashboard/issues/new?labels=bug&template=bug-report.md">Report Bug</a>
    ·
    <a href="https://github.com/yolofarm-smart/dashboard/issues/new?labels=enhancement&template=feature-request.md">Request Feature</a>
  </p>
</div>

## Thông tin nhóm
- Đề tài : `YoloFarm - Smart Farm Dashboard`
- Giảng viên hướng dẫn: `Mai Xuân Toàn`
- Nhiệm vụ : `Xây dựng hệ thống dashboard giám sát và điều khiển`
- Tên nhóm :  🌱 `YoloFarm`
- Phân công công việc chi tiết tại báo cáo hằng tuần: [tại đây](https://drive.google.com/drive/folders/1qbAu0u0LAgKmNhwP9wuEzmYLTMt-VE-C?usp=sharing)
- Danh sách thành viên:

| STT | Họ và Tên           | MSSV    | Vai Trò       | Github                                                     |
| --- | ------------------- | ------- | ------------- |------------                                                |
| 1   | Nguyễn Trọng Kha    | 2211417 | BE Developer     |[KhaLeopard](https://github.com/Nguyentrongkha2k4)          |
| 2 |Lâm Phúc Thịnh             | 2213276    | BE Developer            |...                                                         |
| 3 |Dương Công Phát           | 2212498  | FE Developer        |...                                                         |
| 4 |Hoàng Thiện Bách          | 2210181   |FE Developer        |...                                                         |
| 5 |Nguyễn Quang Tú           |2213851  | IOT      |...                                                         |

## GIỚI THIỆU DỰ ÁN
### Bối cảnh 
Với nhu cầu hiện đại hóa trong nông nghiệp, việc xây dựng hệ thống giám sát và điều khiển môi trường nông trại thông minh là thiết yếu. Các yếu tố như nhiệt độ, độ ẩm, ánh sáng và độ ẩm đất cần được theo dõi và điều chỉnh kịp thời để tối ưu hóa năng suất. Dự án YoloFarm nhằm tạo ra một dashboard thời gian thực, giúp người nông dân dễ dàng kiểm soát và theo dõi từ xa thông qua nền tảng Web.

### Mục tiêu
Xây dựng giao diện dashboard cho nông trại thông minh sử dụng WebSocket để hiển thị dữ liệu cảm biến và điều khiển thiết bị như máy bơm, đèn,... phục vụ cho nhu cầu:

- Giám sát các chỉ số môi trường (nhiệt độ, độ ẩm, ánh sáng, độ ẩm đất)
- Điều khiển thiết bị (bật/tắt từ xa)
- Cảnh báo khi giá trị cảm biến vượt ngưỡng
## 🌟 Tính năng chính
- 📊 **Realtime Dashboard:** hiển thị giá trị cảm biến (nhiệt độ, độ ẩm, ánh sáng, độ ẩm đất) theo thời gian thực.
- 💡 **Điều khiển thiết bị:** bật/tắt máy bơm, đèn thông qua giao diện người dùng.
- 🔔 **Hệ thống cảnh báo:** hiển thị cảnh báo khi giá trị cảm biến vượt ngưỡng an toàn.
- 🧑‍🌾 **Giao diện người dùng thân thiện:** trực quan, dễ sử dụng cho người nông dân, hỗ trợ responsive UI.

## 🏗️ Kiến trúc hệ thống
![System Diagram](./docs/images/system_architecture.png)

- 🖥️ `Frontend`: ReactJS + TailwindCSS - Dashboard giám sát & điều khiển.
- ⚙️ `Backend`: Java Spring Boot - Xử lý logic, WebSocket và API.
- 🌐 `Realtime`: WebSocket - Cập nhật dữ liệu thời gian thực giữa client và server.
- 🔌 `MQTT`: Giao tiếp giữa thiết bị IoT (ESP32) và backend.
- 💾 `Database`: MySQL - Lưu trữ dữ liệu cảm biến, cảnh báo và trạng thái thiết bị.

## 🚀 Công nghệ sử dụng

<div align="center">
  <img src="https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB" />
  <img src="https://img.shields.io/badge/Tailwind_CSS-38B2AC?style=for-the-badge&logo=tailwind-css&logoColor=white" />
  <img src="https://img.shields.io/badge/Java_Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" />
  <img src="https://img.shields.io/badge/WebSocket-010101?style=for-the-badge&logo=websocket&logoColor=white" />
  <img src="https://img.shields.io/badge/MQTT-660066?style=for-the-badge&logo=messagebird&logoColor=white" />
  <img src="https://img.shields.io/badge/MySQL-00758F?style=for-the-badge&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white" />
</div>


## Deployment
Link demo: [https://yolofarm-dashboard.vercel.app](https://smart-yolo-farm.vercel.app/)

## Liên hệ
Nếu bạn có bất kỳ thắc mắc, góp ý hoặc muốn hợp tác, vui lòng liên hệ:
📧 Email: trongkha08022k4@gmail.com  
🔗 GitHub: [Nguyentrongkha2k4](https://github.com/Nguyentrongkha2k4)

<p align="right"><a href="#readme-top">⬆️ Về đầu trang</a></p>

> _Thông tin sẽ được cập nhật thường xuyên khi có chỉnh sửa_
