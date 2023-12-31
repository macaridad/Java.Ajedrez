USE [chess]
GO
/****** Object:  Table [dbo].[matchs]    Script Date: 18/6/2023 01:13:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[matchs](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[player_a] [int] NOT NULL,
	[player_b] [int] NOT NULL,
	[result] [varchar](50) NULL,
	[match_info] [text] NULL,
	[color_b] [varchar](50) NULL,
	[color_a] [varchar](50) NULL,
	[next_color] [varchar](50) NULL,
	[game_date] [datetime] NULL,
	[game_name] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[players]    Script Date: 18/6/2023 01:13:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[players](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name_player] [varchar](50) NULL,
	[score] [int] NULL,
	[king_in_check] [bit] NULL,
	[color] [varchar](50) NULL,
	[player_info] [text] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[matchs]  WITH CHECK ADD  CONSTRAINT [fk_player_a] FOREIGN KEY([player_a])
REFERENCES [dbo].[players] ([id])
GO
ALTER TABLE [dbo].[matchs] CHECK CONSTRAINT [fk_player_a]
GO
ALTER TABLE [dbo].[matchs]  WITH CHECK ADD  CONSTRAINT [fk_player_b] FOREIGN KEY([player_b])
REFERENCES [dbo].[players] ([id])
GO
ALTER TABLE [dbo].[matchs] CHECK CONSTRAINT [fk_player_b]
GO
--correr este script en la base de datos chess en sqlServer2022