CREATE TEMPORARY TABLE proj_by_sal(
  name VARCHAR(60) NOT NULL ,
  totalSal INT NOT NULL
);
INSERT INTO proj_by_sal VALUES ('Game',
                                (SELECT sum(salary) FROM developers
                                WHERE id IN (SELECT id_dev FROM projects WHERE name = 'Game')));

INSERT INTO proj_by_sal VALUES ('Aggregator',
                                (SELECT sum(salary) FROM developers
                                WHERE id IN (SELECT id_dev FROM projects WHERE name = 'Aggregator')));

INSERT INTO proj_by_sal VALUES ('Messenger',
                                (SELECT sum(salary) FROM developers
                                WHERE id IN (SELECT id_dev FROM projects WHERE name = 'Messenger')));

INSERT INTO proj_by_sal VALUES ('Restaurant',
                                (SELECT sum(salary) FROM developers
                                WHERE id IN (SELECT id_dev FROM projects WHERE name = 'Restaurant')));

INSERT INTO proj_by_sal VALUES ('Tip-Tour',
                                (SELECT sum(salary) FROM developers
                                WHERE id IN (SELECT id_dev FROM projects WHERE name = 'Tip-Tour')));

SELECT name FROM proj_by_sal WHERE totalSal = max(totalSal);

