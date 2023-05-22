import type { AppProps } from "next/app";
import "@/styles/globals.css";
import "@/styles/RecommendedSiders.css";
import "@/styles/mySelect.css";

import { GoogleOAuthProvider } from "@react-oauth/google";

import Layout from "@/components/Layout/Layout";

export default function App({ Component, pageProps }: AppProps) {
  return (
      <GoogleOAuthProvider clientId="<your_client_id>">
        <Layout>
          <Component {...pageProps} />
        </Layout>
      </GoogleOAuthProvider>
  );
}
