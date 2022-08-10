BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

insert into prize(prize_name, description, milestone, maxprizes, startdate, enddate)
values('icecream', 'Vanilla Ice cream cone', 10,100 ,'08/09/2022', '08/30/2022' );

insert into book(book_name, description, author, rating, genre)
values('Mybook', 'Book about me', 'Me Author', 1, 'comedy');

COMMIT TRANSACTION;
