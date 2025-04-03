ALTER TABLE product
ADD COLUMN distribution_center VARCHAR(100);

UPDATE product SET distribution_center = 'Mogi das Cruzes' WHERE id IN ('p1', 'p3', 'p5', 'p8', 'p11', 'p14', 'p17', 'p20');
UPDATE product SET distribution_center = 'Recife' WHERE id IN ('p2', 'p6', 'p9', 'p12', 'p15', 'p18');
UPDATE product SET distribution_center = 'Porto Alegre' WHERE id IN ('p4', 'p7', 'p10', 'p13', 'p16', 'p19');
