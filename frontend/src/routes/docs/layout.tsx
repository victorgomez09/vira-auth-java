import { component$, Slot } from "@builder.io/qwik";
import type { RequestHandler } from "@builder.io/qwik-city";
import { constants } from "~/utils/constants";

export const onGet: RequestHandler = async ({ cacheControl, cookie, redirect }) => {
  const jwt = cookie.get(constants.jwtCookieName)
  console.log('jwt', jwt)
  if (jwt == null) {
    console.log('redirect to login')
    throw redirect(302, '/login');
  }
  // Control caching for this request for best performance and to reduce hosting costs:
  // https://qwik.dev/docs/caching/
  cacheControl({
    // Always serve a cached response by default, up to a week stale
    staleWhileRevalidate: 60 * 60 * 24 * 7,
    // Max once every 5 seconds, revalidate on the server to get a fresh version of this page
    maxAge: 5,
  });
};

export default component$(() => {
  return (
    <div class="flex flex-col flex-1">
      <Slot />
    </div>
  );
});
