-- Xóa cơ sở dữ liệu nếu đã tồn tại
IF EXISTS (SELECT * FROM sys.databases WHERE name = 'ASM_JAVA5')
BEGIN
    ALTER DATABASE [ASM_JAVA5] SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE [ASM_JAVA5];
END
GO

-- Tạo cơ sở dữ liệu
CREATE DATABASE [ASM_JAVA5];
GO

-- Chuyển sang sử dụng cơ sở dữ liệu
USE [ASM_JAVA5];
GO

-- Tạo các bảng
SET ANSI_NULLS ON;
GO
SET QUOTED_IDENTIFIER ON;
GO
CREATE TABLE [dbo].[chi_tiet_don_hang] (
    [id] [int] IDENTITY(1,1) NOT NULL,
    [donhangid] [int] NOT NULL,
    [maDHCT] [varchar](100) NOT NULL,
    [soLuong] [int] NOT NULL CHECK ([soLuong] > 0),
    [donGia] [float] NOT NULL CHECK ([donGia] >= 0),
    [diaChi] [nvarchar](200) NOT NULL,
    [so_dien_thoai] [varchar](100) NOT NULL,
    [ngay_dat_hang] [datetime] NULL DEFAULT (GETDATE()),
    [maSP_id] [int] NOT NULL,
    PRIMARY KEY CLUSTERED ([id] ASC)
);
GO
ALTER TABLE [dbo].[chi_tiet_don_hang] 
ADD CONSTRAINT [UQ_chi_tiet_don_hang_maDHCT] UNIQUE ([maDHCT]);
GO

CREATE TABLE [dbo].[don_hang] (
    [id] [int] IDENTITY(1,1) NOT NULL,
    [maDH] [varchar](100) NOT NULL,
    [user_id] [int] NOT NULL,
    [ngay_dat_hang] [date],
    [soLuongDat] [int] NULL CHECK ([soLuongDat] > 0),
    [tongGia] [float] NULL CHECK ([tongGia] >= 0),
    [trangThai] [nvarchar](100) NULL,
    PRIMARY KEY CLUSTERED ([id] ASC)
);
GO
ALTER TABLE [dbo].[don_hang] 
ADD CONSTRAINT [UQ_don_hang_maDH] UNIQUE ([maDH]);
GO

CREATE TABLE [dbo].[gio_hang] (
    [id] [int] IDENTITY(1,1) NOT NULL,
    [soLuong] [int] NOT NULL CHECK ([soLuong] > 0),
    [maSP_id] [int] NOT NULL,
    [user_id] [int] NOT NULL,
    PRIMARY KEY CLUSTERED ([id] ASC)
);
GO

CREATE TABLE [dbo].[loai_hang] (
    [id] [int] IDENTITY(1,1) NOT NULL,
    [tenLoaiSP] [nvarchar](100) NOT NULL,
    [moTa] [nvarchar](100) NULL,
    PRIMARY KEY CLUSTERED ([id] ASC)
);
GO

CREATE TABLE [dbo].[san_pham] (
    [id] [int] IDENTITY(1,1) NOT NULL,
    [maSP] [varchar](100) NOT NULL,
    [tenSP] [nvarchar](100) NOT NULL,
    [gia] [float] NOT NULL CHECK ([gia] >= 0),
    [soLuong] [int] NOT NULL CHECK ([soLuong] >= 0),
    [anhSP] [nvarchar](max) NULL,
    [moTa] [nvarchar](200) NULL,
    [xuatSu] [nvarchar](100) NULL,
    [category_id] [int] NOT NULL,
    PRIMARY KEY CLUSTERED ([id] ASC)
);
GO
ALTER TABLE [dbo].[san_pham] 
ADD CONSTRAINT [UQ_san_pham_maSP] UNIQUE ([maSP]);
GO

CREATE TABLE [dbo].[users] (
    [id] [int] IDENTITY(1,1) NOT NULL,
    [username] [nvarchar](100) NOT NULL,
    [password] [varchar](100) NOT NULL,
    [email] [varchar](100) NOT NULL,
    [hoTen] [nvarchar](100) NOT NULL,
    [vaiTro] [bit] NOT NULL,
    [ngayTao] [date] NULL DEFAULT (GETDATE()),
    PRIMARY KEY CLUSTERED ([id] ASC)
);
GO
ALTER TABLE [dbo].[users] 
ADD CONSTRAINT [UQ_users_email] UNIQUE ([email]);
GO

-- Thêm khóa ngoại
ALTER TABLE [dbo].[chi_tiet_don_hang]  
ADD CONSTRAINT [FK_chi_tiet_don_hang_donhangid] 
FOREIGN KEY ([donhangid]) REFERENCES [dbo].[don_hang] ([id]) 
ON DELETE CASCADE;
GO

ALTER TABLE [dbo].[chi_tiet_don_hang]  
ADD CONSTRAINT [FK_chi_tiet_don_hang_maSP] 
FOREIGN KEY ([maSP_id]) REFERENCES [dbo].[san_pham] ([id]);
GO

ALTER TABLE [dbo].[don_hang]  
ADD CONSTRAINT [FK_don_hang_user_id] 
FOREIGN KEY ([user_id]) REFERENCES [dbo].[users] ([id]);
GO

ALTER TABLE [dbo].[gio_hang]  
ADD CONSTRAINT [FK_gio_hang_maSP] 
FOREIGN KEY ([maSP_id]) REFERENCES [dbo].[san_pham] ([id]);
GO

ALTER TABLE [dbo].[gio_hang]  
ADD CONSTRAINT [FK_gio_hang_user_id] 
FOREIGN KEY ([user_id]) REFERENCES [dbo].[users] ([id]);
GO

ALTER TABLE [dbo].[san_pham]  
ADD CONSTRAINT [FK_san_pham_category_id] 
FOREIGN KEY ([category_id]) REFERENCES [dbo].[loai_hang] ([id]);
GO

-- Chèn dữ liệu cho bảng loai_hang (chỉ các loại đồng hồ)
INSERT INTO [dbo].[loai_hang] ([tenLoaiSP], [moTa]) VALUES
('Đồng hồ thể thao', 'Đồng hồ thông minh hỗ trợ thể thao'),
('Đồng hồ thời trang', 'Đồng hồ thông minh thiết kế thời trang'),
('Đồng hồ sức khỏe', 'Đồng hồ thông minh theo dõi sức khỏe'),
('Đồng hồ cao cấp', 'Đồng hồ thông minh cao cấp'),
('Đồng hồ giá rẻ', 'Đồng hồ thông minh giá phải chăng'),
('Đồng hồ đa năng', 'Đồng hồ thông minh tích hợp nhiều tính năng'),
('Đồng hồ trẻ em', 'Đồng hồ thông minh dành cho trẻ em'),
('Đồng hồ doanh nhân', 'Đồng hồ thông minh cho doanh nhân'),
('Đồng hồ công nghệ', 'Đồng hồ thông minh với công nghệ tiên tiến'),
('Đồng hồ cổ điển', 'Đồng hồ thông minh phong cách cổ điển');
GO

-- Chèn dữ liệu cho bảng san_pham (phân bổ theo các loại đồng hồ)
INSERT INTO [dbo].[san_pham] ([maSP], [tenSP], [gia], [soLuong], [anhSP], [moTa], [xuatSu], [category_id]) VALUES
('SP001', 'Apple Watch Series 7', 10000, 50, 'applewatch7.jpg', 'Đồng hồ cao cấp của Apple', 'USA', 4),
('SP002', 'Samsung Galaxy Watch 4', 9000, 40, 'galaxywatch4.jpg', 'Đồng hồ đa năng của Samsung', 'Korea', 6),
('SP003', 'Garmin Venu 2', 12000, 30, 'garminvenu2.jpg', 'Đồng hồ thể thao thông minh', 'USA', 1),
('SP004', 'Fitbit Versa 3', 8000, 20, 'fitbitversa3.jpg', 'Đồng hồ theo dõi sức khỏe', 'USA', 3),
('SP005', 'Huawei Watch GT 3', 9500, 25, 'huaweigt3.jpg', 'Đồng hồ thời trang của Huawei', 'China', 2),
('SP006', 'Xiaomi Mi Watch', 6000, 30, 'miwatch.jpg', 'Đồng hồ giá rẻ của Xiaomi', 'China', 5),
('SP007', 'TicWatch Pro 3', 11000, 15, 'ticwatchpro3.jpg', 'Đồng hồ công nghệ cao', 'China', 9),
('SP008', 'Fossil Gen 6', 13000, 10, 'fossilgen6.jpg', 'Đồng hồ doanh nhân thời trang', 'USA', 8),
('SP009', 'Oppo Watch 2', 8500, 60, 'oppowatch2.jpg', 'Đồng hồ trẻ em thông minh', 'China', 7),
('SP010', 'Amazfit GTR 3', 7000, 70, 'amazfitgtr3.jpg', 'Đồng hồ cổ điển thông minh', 'China', 10);
GO

-- Chèn dữ liệu cho bảng users
INSERT INTO [dbo].[users] ([username], [password], [email], [hoTen], [vaiTro], [ngayTao]) VALUES
('user01', 'password123', 'user01@example.com', 'Nguyen A', 0, '2025-03-07'),
('user02', 'password123', 'user02@example.com', 'Nguyen B', 0, '2025-03-07'),
('user03', 'password123', 'user03@example.com', 'Nguyen C', 0, '2025-03-07'),
('user04', 'password123', 'user04@example.com', 'Nguyen D', 0, '2025-03-07'),
('user05', 'password123', 'user05@example.com', 'Nguyen E', 0, '2025-03-07'),
('user06', 'password123', 'user06@example.com', 'Nguyen F', 0, '2025-03-07'),
('admin01', 'admin123', 'admin01@example.com', 'Admin A', 1, '2025-03-07'),
('admin02', 'admin123', 'admin02@example.com', 'Admin B', 1, '2025-03-07'),
('admin03', 'admin123', 'admin03@example.com', 'Admin C', 1, '2025-03-07'),
('admin04', 'admin123', 'admin04@example.com', 'Admin D', 1, '2025-03-07');
GO

-- Chèn dữ liệu cho bảng don_hang
INSERT INTO [dbo].[don_hang] ([maDH], [user_id], [ngay_dat_hang], [soLuongDat], [tongGia], [trangThai]) VALUES
('DH001', 1, '2025-03-07', 2, 20000, 'Đã giao'),
('DH002', 2, '2025-03-07', 1, 9000, 'Chờ xác nhận'),
('DH003', 3, '2025-03-07', 3, 36000, 'Đã giao'),
('DH004', 4, '2025-03-07', 2, 16000, 'Đang xử lý'),
('DH005', 5, '2025-03-07', 1, 9500, 'Đã giao'),
('DH006', 6, '2025-03-07', 5, 30000, 'Chờ xác nhận'),
('DH007', 7, '2025-03-07', 1, 11000, 'Đang xử lý'),
('DH008', 8, '2025-03-07', 2, 26000, 'Đã giao'),
('DH009', 9, '2025-03-07', 3, 25500, 'Chờ xác nhận'),
('DH010', 10, '2025-03-07', 4, 28000, 'Đang xử lý');
GO

-- Chèn dữ liệu cho bảng gio_hang
INSERT INTO [dbo].[gio_hang] ([soLuong], [maSP_id], [user_id]) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(1, 4, 4),
(2, 5, 5),
(1, 6, 6),
(2, 7, 7),
(3, 8, 8),
(1, 9, 9),
(2, 10, 10);
GO

-- Chèn dữ liệu cho bảng chi_tiet_don_hang
INSERT INTO [dbo].[chi_tiet_don_hang] ([donhangid], [maDHCT], [soLuong], [donGia], [diaChi], [so_dien_thoai], [ngay_dat_hang], [maSP_id]) VALUES
(1, 'DHCT001', 2, 10000, 'Hà Nội', '0901234567', '2025-03-07', 1),
(2, 'DHCT002', 1, 9000, 'TPHCM', '0912345678', '2025-03-07', 2),
(3, 'DHCT003', 3, 12000, 'Đà Nẵng', '0923456789', '2025-03-07', 3),
(4, 'DHCT004', 2, 8000, 'Hải Phòng', '0934567890', '2025-03-07', 4),
(5, 'DHCT005', 1, 9500, 'Cần Thơ', '0945678901', '2025-03-07', 5),
(6, 'DHCT006', 5, 6000, 'Hà Nội', '0956789012', '2025-03-07', 6),
(7, 'DHCT007', 1, 11000, 'TPHCM', '0967890123', '2025-03-07', 7),
(8, 'DHCT008', 2, 13000, 'Đà Nẵng', '0978901234', '2025-03-07', 8),
(9, 'DHCT009', 3, 8500, 'Hải Phòng', '0989012345', '2025-03-07', 9),
(10, 'DHCT010', 4, 7000, 'Cần Thơ', '0990123456', '2025-03-07', 10);
GO