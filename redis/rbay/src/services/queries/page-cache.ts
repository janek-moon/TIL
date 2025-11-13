import { client } from '$services/redis';
import { pageCacheKey } from '$services/keys';

const chcheRoutes = ['/about', '/privacy', '/auth/signin', '/auth/signup'];

export const getCachedPage = async (route: string) => {
	if (chcheRoutes.includes(route)) {
		return client.get(pageCacheKey(route));
	}

	return null;
};

export const setCachedPage = (route: string, page: string) => {
	if (chcheRoutes.includes(route)) {
		return client.set(pageCacheKey(route), page, { EX: 60 * 5 });
	}

	return null;
};
