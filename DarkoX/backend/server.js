// Starter only. Add a database, authentication, audit logging and independent security review before production.
const express = require('express');
const helmet = require('helmet');
const rateLimit = require('express-rate-limit');
const app = express();
app.use(helmet());
app.use(express.json({limit:'20kb'}));
app.use(rateLimit({windowMs:60_000, max:60}));
const PORT = process.env.PORT || 8080;
const config = {
  network: 'Base Mainnet',
  chainId: 8453,
  tokenName: process.env.TOKEN_NAME || 'Darko X',
  tokenSymbol: process.env.TOKEN_SYMBOL || 'DKX',
  tokenContractAddress: process.env.TOKEN_CONTRACT_ADDRESS || '',
  rewardsEnabled: false
};
app.get('/health', (_req,res)=>res.json({ok:true,service:'darkox-api'}));
app.get('/v1/config', (_req,res)=>res.json(config));
// Intentionally no public endpoint that credits tokens. Implement rewards only after
// server-side ad-provider callbacks, anti-fraud checks, user consent and idempotency.
app.get('/v1/rewards/status', (_req,res)=>res.status(503).json({
  enabled:false, message:'Rewards are not configured. No tokens are being issued.'
}));
// TODO: Add admin routes protected by strong authentication (MFA), role checks and audit logs.
// Never trust reward amounts, ad views, device clocks or balances supplied by the app.
app.listen(PORT, ()=>console.log(`Darko X API listening on ${PORT}`));
