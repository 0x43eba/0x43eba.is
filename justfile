default:
	just -l

run:
	pnpm run dev

deploy:
	fly deploy