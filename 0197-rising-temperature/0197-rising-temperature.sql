# Write your MySQL query statement below
select w.id from Weather w join Weather w1 on w.recordDate=DATE_ADD(w1.recordDate,INTERVAL 1 Day)
where w.temperature>w1.temperature;
